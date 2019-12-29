package com.cy.pj.sys.service;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysStudents;

public interface SysStudentsService {
	
	SysStudents findStudentById(Integer id);
	
	int deleteObjects(Integer...ids);

	int updateObject(SysStudents entity);
	
	int insertStudent(SysStudents sysStudents);
	
	PageObject<SysStudents> findPageObject(String sname,Integer pageCurrent);
	
}
