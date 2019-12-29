package com.cy.pj.sys.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.vo.SysRoleMenuVo;

@Mapper
public interface SysRoleDao {
	
	//添加操作,和用户关联
	List<CheckBox> findObjects();
	
	//更新操作
	int updateObject(SysRole entity);
	
	//更新页面数据呈现操作
	SysRoleMenuVo findObjectById(Integer id);
	
	//添加操作
	int insertObject(SysRole entity);
	
	//删除操作
	int deleteObject(Integer id);
	
	/**
	 * 基于条件查询总记录数(模糊查询name)
	 */
	int getRowCount(@Param("name") String name);
	
	/**
	 * 基于掉件分页查询角色信息,返回的是list集合,信息封装到SysRole实体类中
	 * 意思就是对查询的页面进行封装,设定范围
	 */
    List<SysRole> findPageObjects(@Param("name") String name,
    		@Param("startIndex") Integer startIndex,
    		@Param("pageSize") Integer pageSize);
}
