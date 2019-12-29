package com.cy.pj.sys.service;
import java.util.List;
import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.vo.SysRoleMenuVo;
public interface SysRoleService {
	
	//添加操作
	List<CheckBox> findObjects();
	
	int updateObject(SysRole entity,Integer[] menuIds);
	
	SysRoleMenuVo findObjectById(Integer id);
	
	//从关系数据中得到
	int saveObject(SysRole entity,Integer[]menuIds);
	
	int deleteObject(Integer id);
	
	int getRowCount(String name);
	
	//返回的就是分页查询角色信息数据了,根据name和pageCurrent盘点
	PageObject<SysRole> findPageObjects(String name,
    		                            Integer pageCurrent);

}
