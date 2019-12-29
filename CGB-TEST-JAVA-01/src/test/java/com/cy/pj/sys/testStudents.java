package com.cy.pj.sys;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cy.pj.sys.dao.SysStudentsDao;
import com.cy.pj.sys.entity.SysStudents;

@SpringBootTest
public class testStudents {
	@Autowired
	private SysStudentsDao sysStudentsDao;
	
	@Test
	public void testGetRowCount() {
		int rowCount = sysStudentsDao.getRowCount("小乔");
		System.out.println(rowCount);
	}
	
	@Test
	public void testFindPageObject() {
		List<SysStudents> rows = sysStudentsDao.findPageObject("大乔", 1, 2);
		for (SysStudents s : rows) {
			System.out.println(s);
			System.out.println(rows);
		}
	}

}
