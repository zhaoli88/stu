package com.cy.pj.sys.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.cy.pj.sys.entity.SysCoach;

@Mapper
public interface SysCoachDao {
	
	//删除
	int deleteObject(Integer id);
	//查询栏查询实现
	SysCoach findCoachById(Integer id);
	//添加
	int insertCoach(SysCoach entity);
	//更新
	int updateObject(SysCoach entity);
	
	/**
	 * 展示信息到页面
	 * @param cname
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<SysCoach> findPageObjects(@Param("cname")String cname,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	int getRowCount(@Param("cname")String cname);

}
