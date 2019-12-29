package com.cy.sys.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.sys.entity.SysProject;

@Mapper
public interface SysProjectDao {

	/**
	 * 根据分类和页码值来查询数据，返回值都是一个集合
	 * @param qc
	 * @return
	 */
	List<SysProject> selectObjects(@Param("startIndex")Integer startIndex,
								@Param("pageSize")Integer pageSize,
								@Param("categoryId")Integer categoryId);
	
	
	/**
	 * 查询出所有的记录数
	 * @return
	 */
	Integer getRowCount(@Param("categoryId")Integer categoryId);
	
	/**
	 * @param name
	 * @param pageCurrent
	 * @param pageSize
	 * @return
	 */
	List<SysProject> findProjectByName(@Param("name")String name);
	
	
	/**
	 * 根据项目名查询出项目信息
	 * @param filmNames
	 * @return
	 */
	List<SysProject> findObjectsByFilmNames(@Param("filmNames")List<String> filmNames);
	
}
