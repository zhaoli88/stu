package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户和角色的关系数据表 
 * @author 44734
 *
 */
@Mapper
public interface SysUserRoleDao {

	//通过用户id找到角色id们
	List<Integer> findRoleIdsByUserId(Integer id);
	
	/**
	  * 将用户和角色的关系数据存储到数据库
	  * @param userId
	  * @param roleIds
	  * @return
	  */
	  int insertObjects(
			   @Param("userId")Integer userId,
			   @Param("roleIds")Integer[]roleIds);
	
	//根据角色id删除信息
	int deleteObjectsByRoleId(Integer roleId);
	
	//根据用户id删除自身信息
	int deleteIbejctByUserId(Integer userId);

}
