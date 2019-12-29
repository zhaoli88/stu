package com.cy.pj.sys.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.cy.pj.common.config.PageProperties;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;
import com.cy.pj.sys.vo.SysRoleMenuVo;
import io.micrometer.core.instrument.util.StringUtils;
@Service
public class SysRoleServiceImpl implements SysRoleService{

	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	@Autowired
	private PageProperties pageProerties;
	
	@Override
	public int getRowCount(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageObject<SysRole> findPageObjects(String name,
			                                   Integer pageCurrent) {
		//1.验证参数合法性
		Assert.isTrue(pageCurrent!=null||pageCurrent>0, "当前页码值无效");
		//2.查询总记录数
		int rowCount = sysRoleDao.getRowCount(name);
		//校验参数合法性
		if(rowCount==0)
			throw new ServiceException("没有找到记录");
		//3.查询当前页记录,套用公式
		int pageSize=pageProerties.getPageSize();
		int startIndex=(pageCurrent-1)*pageSize;
		//查到当前页记录数
		List<SysRole> records = sysRoleDao.findPageObjects(name, 
				                   startIndex,
				                   pageSize);
		//对查询结果进行封装
		System.out.println(""+pageCurrent+pageSize+rowCount+records);
		return new PageObject<>(pageCurrent,pageSize,rowCount,records);
	}


	@Override
	public int deleteObject(Integer id) {
		//1.验证参数合法性
		Assert.isTrue(id!=null||id>0, "请先选中id");
		//2.基于id删除关系数据 user_role   role_menu
		sysUserRoleDao.deleteObjectsByRoleId(id);
		sysRoleMenuDao.deleteObjectsByRoleId(id);
		//3.删除自身信息
		int rows = sysRoleDao.deleteObject(id);
		if(rows==0)
			throw new ServiceException("数据已经不存在");
		//4.返回结果
		return rows;
	}

	@Override
	public int saveObject(SysRole entity,
			              Integer[] menuIds) {
		//1.验证参数有效性
		Assert.isTrue(entity!=null, "保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("名字不能为空");
		Assert.isTrue(menuIds!=null||menuIds.length!=0, "必须为角色分配权限");
		//2.保存角色信息
		int rows = sysRoleDao.insertObject(entity);
		if(rows==0)
			throw new ServiceException("没有保存到数据");
		//3.保存角色菜单关系数据?????怎么理解  entity.getId()意思是上面语句执行之后就有id了
		sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
		//4.返回结果
		return rows;
	}

	@Override
	public SysRoleMenuVo findObjectById(Integer id) {
		//1.合法性校验
		Assert.isTrue(id!=null&&id>0, "请选中id");
		//2.执行查询
		SysRoleMenuVo result = sysRoleDao.findObjectById(id);
		//3.验证结果并返回
		if(result==null)
			throw new ServiceException("查询结果已丢失");
		return result;
	}

	@Override
	public int updateObject(SysRole entity,
			                Integer[] menuIds) {
		//1.验证参数合法性
		Assert.isTrue(entity!=null, "更新对象不能为空");
		Assert.isTrue(entity.getId()!=null, "id值不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("角色名不能为空");
		Assert.isTrue(menuIds!=null||menuIds.length!=0, "必须为角色指定一个权限");
		//2.更新数据
		int rows = sysRoleDao.updateObject(entity);
		
		if(rows==0)
			throw new ServiceException("对象可能消失");
         //??删除role_menu关系信息    更新其id
		sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
		sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
		//3.返回结果
		return rows;
	}

	@Override
	public List<CheckBox> findObjects() {
		return sysRoleDao.findObjects();
	}


	


}
