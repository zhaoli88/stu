package com.cy.pj.sys;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cy.pj.sys.dao.SysSiteDao;
import com.cy.pj.sys.entity.SysSite;

@SpringBootTest
public class testSite {
	@Autowired
	private SysSiteDao sysSiteDao;
	
	@Test
	public void testPageObejcts() {
		List<SysSite> rows = sysSiteDao.findPageObjects("牛欢喜老火锅", 1, 2);
		for (SysSite sysSite : rows) {
			System.out.println(sysSite);
		}
	}

}
