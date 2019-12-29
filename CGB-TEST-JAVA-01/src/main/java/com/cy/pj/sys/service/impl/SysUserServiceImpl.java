package com.cy.pj.sys.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import com.cy.pj.common.annotation.LogAnnotation;
import com.cy.pj.common.config.PageProperties;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysUserService;
import com.cy.pj.sys.vo.SysUserDeptVo;

/*@Transactional(timeout = 30,
               readOnly = false,
               isolation = Isolation.READ_COMMITTED,
               rollbackFor = Propagation.REQUIRED)*/
@Service
public class SysUserServiceImpl implements SysUserService{
	
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Autowired
	private PageProperties pageProperties;

	@LogAnnotation
	@Override
	public PageObject<SysUserDeptVo> findPageObjects(String username,
			                                         Integer pageCurrent) {
		//1.验证参数合法性
		Assert.isTrue(pageCurrent!=null||pageCurrent>0, "页码值为无效值");
	    //2.查询总记录数并验证rowCount
		int rowCount = sysUserDao.getRowCount(username);
		if(rowCount==0)
			throw new ServiceException("没有找到总记录数数据");
		//3.查询当前页记录数startIndex=(rowCount-1)*pageSize
		//3.1定义当前页面大小,可根据需求合理定义
		int pageSize=pageProperties.getPageSize();
		int startIndex=(pageCurrent-1)*pageSize;
		
		List<SysUserDeptVo> records = 
				sysUserDao.findPageObjects(username, startIndex, pageSize);
		//4.对结果进行封装并返回 new PageObject();
		return new PageObject<>(pageCurrent,pageSize,rowCount,records);
	}

	@RequiresPermissions("sys:system")
	@LogAnnotation(operation = "启用禁用")
	@Override
	public int validById(Integer id, Integer valid, String modifiedUser) {
		//1.验证参数合法性
		Assert.isTrue(id!=null||id>0, "id参数不合法:"+id);
		//Assert.isTrue(valid!=1&&valid!=0, "valid参数不合法:"+valid);
        if(valid!=1&&valid!=0)
        	throw new ServiceException("valid参数不合法:"+valid);
		if(StringUtils.isEmpty(modifiedUser))
        	throw new ServiceException("修改用户不能为空");
		//2.执行禁用或启用操作
		int rows = sysUserDao.validById(id, valid, modifiedUser);
		if(rows==0)
			throw new ServiceException("此记录已经丢失");
		//3.判定结果并返回
		return rows;
	}

	@RequiresPermissions("sys:system")
	@LogAnnotation
	@Override
	public int saveObject(SysUser entity, Integer[] roleIds) {
        //1.校验参数合法性
		if(entity==null)
    		throw new ServiceException("保存对象不能为空");
    	if(StringUtils.isEmpty(entity.getUsername()))
    		throw new ServiceException("用户名不能为空");
    	if(StringUtils.isEmpty(entity.getPassword()))
    		throw new ServiceException("密码不能为空");
    	if(roleIds==null || roleIds.length==0)
    		throw new ServiceException("至少要为用户分配角色");
    	//2.保存用户自身信息
        //2.1对密码进行加密
    	String source=entity.getPassword();
    	//2.2加盐值
    	String salt=UUID.randomUUID().toString();
    	SimpleHash sh = new SimpleHash("MD5",      //算法名称
    			                        source,    //未加密的密码
    			                        salt,      //盐值,防止暴力加密
    			                        1);        //加密次数
		//2.3将盐值和加密后的密码set进entity,,toHex()将对象转成16进制的
    	entity.setSalt(salt);
		entity.setPassword(sh.toHex());
		int rows = sysUserDao.insertObject(entity);
    	//3.保存用户角色关系数据
		sysUserRoleDao.insertObjects(entity.getId(), roleIds);
		
		//4.返回结果
		if(rows==0)
			throw new ServiceException("数据可能已近丢失");
		return rows;
	}

	@Override
	public Map<String, Object> findObejctById(Integer userId) {
		//1.参数合法性判断
		Assert.isTrue(userId!=null||userId>0, "参数不合法userId:"+userId);
		//2.业务查询
		//2.1通过userId查询到Object
		SysUserDeptVo user = sysUserDao.findObjectById(userId);
		if(user==null)
			throw new ServiceException("此用户已经不存在");
		//2.2通过UserID查询到角色ids
		List<Integer> roleIds = 
				sysUserRoleDao.findRoleIdsByUserId(userId);
		//3.数据封装  Map<String,Object> map=new HashMap<>();因为有两个需要封装的值
		Map<String,Object> map = new HashMap<>();
		map.put("user", user);
		map.put("roleIds", roleIds);
		//4.返回结果
		return map;
	}

	@LogAnnotation
	@Override
	public int updateObject(SysUser entity, Integer[] roleIds) {
        //1.验证参数合法性
		Assert.isTrue(entity!=null, "保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
			throw new ServiceException("用户名不能为空");
		Assert.isTrue(roleIds!=null||roleIds.length!=0, "必须为角色指定角色");
		//2.更新操作
		int rows = sysUserDao.updateObject(entity);
		if(rows==0)
			throw new ServiceException("没有更新数据");
		//3.保存用户与角色关系数据
		sysUserRoleDao.deleteIbejctByUserId(entity.getId());
		sysUserRoleDao.insertObjects(entity.getId(), roleIds);
		//4.返回结果
		return rows;
	}

	@LogAnnotation
	@Override
	public int updatePassword(String password, 
			                  String newPassword,
			                  String cfgPassword) {
        //1.判断参数信息
		if(StringUtils.isEmpty(newPassword))
			throw new ServiceException("请输入新密码");
		if(StringUtils.isEmpty(cfgPassword))
			throw new ServiceException("请确认新密码");
		if(!newPassword.equals(cfgPassword))
			throw new IllegalArgumentException("两次输入的密码不相等");
		//2.判断原密码是否正确
		if(StringUtils.isEmpty(password))
			throw new ServiceException("请输入需要更新的密码");
		//2.1获取登录用户???
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		//2.2使用MD5算法,判断原密码是否正确
		SimpleHash sh=new SimpleHash("MD5",
				                      password,
				                      user.getSalt(),
				                      1);
		if(!user.getPassword().equals(sh.toHex()))
			throw new ServiceException("原密码不正确");
		//3.对新密码加密,利用盐值
		String salt=UUID.randomUUID().toString();
		sh=new SimpleHash("MD5",newPassword,salt,1);
		//4.将新密码加密后的结果更新到数据库
		int rows = sysUserDao.updatePassword(sh.toHex(),  //加密后的密码
	                		                 salt,        //新生产的盐值
				                             user.getId());//用户ID
		if(rows==0)
			throw new ServiceException("修改密码失败");
		return rows;
	}

	
	//公用模板
	@Override
	public int isExist(String columnName, String columnValue) {
		//1.验证参数合法性
		if(StringUtils.isEmpty(columnName))
			throw new ServiceException("条件名A不能为空,(A=B)");
		if(StringUtils.isEmpty(columnValue))
			throw new ServiceException("查询条件B不能为空,(A=B)");
		
        //2.执行查询业务
		int rows = sysUserDao.isExist("sys_users", columnName, columnValue);
		if(rows>0)
			throw new ServiceException(columnName+"已经存在(模板)");
		return rows;
	}

	
	

}
