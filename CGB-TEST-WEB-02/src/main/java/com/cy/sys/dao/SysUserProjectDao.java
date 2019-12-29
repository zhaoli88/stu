package com.cy.sys.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserProjectDao {

	/**
	 * 根据userId查询出项目名称
	 * @param userId
	 * @return
	 */
	List<String> findObjectById(Integer userId);
	
	/**
	 * 插入userId和项目名
	 * @param userId
	 * @param filmName
	 * @return
	 */
	int insertObjectByName(@Param("userId")Integer userId,@Param("filmName")String filmName);
}
