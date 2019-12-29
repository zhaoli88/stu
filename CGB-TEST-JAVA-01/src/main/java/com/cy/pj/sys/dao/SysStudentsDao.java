package com.cy.pj.sys.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.cy.pj.sys.entity.SysStudents;

@Mapper
public interface SysStudentsDao {
	
	SysStudents findStudentById(Integer id);

    int updateObject(SysStudents entity);
	
	int deleteObjects(@Param("ids")Integer...ids);
	
	int insertStudent(SysStudents sysStudents);
	
	List<SysStudents> findPageObject(
			@Param("sname") String sname,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	/**依据条件统计记录总数*/
	int getRowCount(@Param("sname") String sname);
}
