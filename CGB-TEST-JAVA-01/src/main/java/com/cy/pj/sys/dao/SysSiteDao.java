package com.cy.pj.sys.dao;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.cy.pj.sys.entity.SysSite;

@Mapper
public interface SysSiteDao {
	
	List<SysSite> findPageObjects(@Param("name")String name,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	int getRowCount(@Param("name")String name);
	
	/**
	 * 基于菜单id获取权限标识
	 * @param menuIds
	 * @return
	 */
	List<String> findPermissions(
			@Param("menuIds") Integer... menuIds);
	/**
	 * 更新菜单信息
	 * @param entity
	 * @return
	 */
	int updateObject(SysSite entity);
	/**
	 * 写入菜单信息
	 * @param entity
	 * @return
	 */
	int insertObject(SysSite entity);
	
	int deleteObject(Integer id);

	List<Map<String,Object>> findObjects();
	

}
