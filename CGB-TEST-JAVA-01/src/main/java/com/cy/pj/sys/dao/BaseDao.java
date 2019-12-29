package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 集中共性代码
 * @author 44734
 *
 */
public interface BaseDao {
	
	@Select("select count(*) from ${tableName} where ${columnName}=#{columnValue}")
	int isExist(@Param("tableName")String tableName,
			    @Param("columnName")String columnName,
			    @Param("columnValue")String columnValue);

}
