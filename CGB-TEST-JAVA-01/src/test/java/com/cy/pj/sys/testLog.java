package com.cy.pj.sys;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;

@SpringBootTest
public class testLog {
	@Autowired
	private SysLogDao sysLogDao;
	
	@Test
	public void testDeleteLogsById() {
		int rows = sysLogDao.deleteLogsById(174,175);
		System.out.println(rows);
	}
	
	@Test
	public void testFindPageObjects() {
		List<SysLog> rows = sysLogDao.findPageObjects("自动挡查c1", 1, 2);
		for (SysLog sysLog : rows) {
			System.out.println(sysLog);
		}
	}

}
