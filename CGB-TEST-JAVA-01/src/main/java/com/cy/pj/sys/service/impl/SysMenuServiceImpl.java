package com.cy.pj.sys.service.impl;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService{
	
	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Override
	public List<Map<String, Object>> findObjects() {
		List<Map<String, Object>> list = sysMenuDao.findObjects();
		//判断结果集
		if(list==null||list.size()==0)
			throw new ServiceException("没有对应菜单");
		return list;
	}

	@Override
	public int deleteObject(Integer id) {
		//1.判断参数的有效性
		/*
		 * if(id==0||id<0) throw new IllegalArgumentException("请先选中id");
		 */
		Assert.isTrue(id!=null&&id>1, "请先选中id");
		//2.查询当前菜单是否有子菜单,并校验
		int count = sysMenuDao.getChildCount(id);
		if(count>0)
			throw new ServiceException("请先删除子菜单");
		//3.删除关系菜单的信息,物理上没有关系,逻辑上有关系
		sysRoleMenuDao.deleteObjectsByMenuId(id);
		//4.再删除菜单元素
		int rows = sysMenuDao.deleteObject(id);
		if(rows==0)
		throw new ServiceException("此菜单可能不存在了");
		//5.返回结果
		return rows;
	}

	@Override
	public List<Node> findZtreeMenuNodes() {
		return sysMenuDao.findZtreeMenuNodes();
	}

	@Override
	public int insertObject(SysMenu entity) {
		//1.验证参数合法性
		 //if(entity==null) throw new ServiceException("保存对象不能为空");
		Assert.isTrue(entity!=null, "没有内容被保存");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("菜单名不能为空");
		//2.保存数据
		int rows;
		try {
			rows = sysMenuDao.insertObject(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("保存失败");
		}
		//3.返回数据
		return rows;
	}

	@Override
	public int updateObject(SysMenu entity) {
		//1.合法验证
		Assert.isTrue(entity!=null, "保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
		throw new ServiceException("菜单名不能为空");
		//2.更新数据
		int rows = sysMenuDao.updateObject(entity);
		if(rows==0)
			throw new ServiceException("记录可能已近不存在");
		//3.返回数据
		return rows;
	}

}
