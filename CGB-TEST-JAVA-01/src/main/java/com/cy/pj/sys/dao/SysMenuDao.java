package com.cy.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.entity.SysMenu;

@Mapper
public interface SysMenuDao {
	
	//基于id查询权限表示的方法
	List<String> findPermissions(@Param("menuIds")Integer[] menuIds);
	
	//接口中添加数据更新方法
	int updateObject(SysMenu entity);
	
	//添加菜单信息,将数据封装到entity
	int insertObject(SysMenu entity);
	
	//添加菜单信息,用于查询上级菜单的信息
	List<Node> findZtreeMenuNodes();
	
	//基于菜单id删除菜单记录的方法
	int deleteObject(Integer id);
	
	//根据菜单id查询子菜单记录的方法
	int getChildCount(Integer id);

	//基于方法进行查询操作
	List<Map<String,Object>> findObjects();
}
