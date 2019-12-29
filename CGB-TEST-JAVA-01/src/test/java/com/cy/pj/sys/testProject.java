package com.cy.pj.sys;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cy.pj.sys.dao.SysProjectDao;
import com.cy.pj.sys.entity.SysProject;

@SpringBootTest
public class testProject {
	@Autowired
	private SysProjectDao sysProjectDao;
	
	@Test
	public void testUpdateObject() {
		SysProject entity = new SysProject();
		entity.setId(1);
		int rows = sysProjectDao.updateObject(entity);
		System.out.println(rows);
	}
	
	@Test
	public void testfindPageObjects() {
		List<Map<String, Object>> rows = sysProjectDao.findPageObjects("admin", 1, 2);
		for (Map<String, Object> map : rows) {
			System.out.println(map);
		}
	}

}
