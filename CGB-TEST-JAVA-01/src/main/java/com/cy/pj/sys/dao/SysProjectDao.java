package com.cy.pj.sys.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.sys.entity.SysProject;

@Mapper
public interface SysProjectDao {
	
	List<SysProject> findObjects(
			@Param("name")String name,
			@Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);
	
	int insertProject(SysProject entity);
	
	SysProject findProjectById(Integer id);
	
	int updateObject(SysProject entity);
	
	int delteObjects(@Param("ids")Integer...ids);
	
	List<Map<String,Object>> findPageObjects(
			@Param("name")String name,
			@Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);
	
	int getRowCount(@Param("name")String name);

}
