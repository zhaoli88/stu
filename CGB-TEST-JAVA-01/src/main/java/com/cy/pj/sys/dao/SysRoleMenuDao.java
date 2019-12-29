package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysRoleMenuDao {
	
	//?????根据roleId查询菜单ids
	int findMenuIdsByRoleId(Integer roleId);
	
	/**
	  * 基于多个角色id获取多个菜单id
	 * @param roleIds
	 * @return
	 */
	List<Integer> findMenuIdsByRoleIds(
			@Param("roleIds")Integer[] roleIds);
	
	int insertObjects(@Param("roleId") Integer roleId,
			          @Param("menuIds") Integer[] menuIds);
	
	int deleteObjectsByRoleId(Integer roleId);
	
	//根据菜单id进行删除角色和菜单信息
	int deleteObjectsByMenuId(Integer menuId);

}
