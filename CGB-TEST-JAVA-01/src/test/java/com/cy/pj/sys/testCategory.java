package com.cy.pj.sys;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.dao.SysCategoryDao;

@SpringBootTest
public class testCategory {
	@Autowired
	private SysCategoryDao sysCategoryDao;
	
	@Test
	public void testFindObjectById() {
		Node rows = sysCategoryDao.findObjectById(2);
		System.out.println(rows);
	}
	
	@Test
	public void testFindCategorys() {
		List<Node> rows = sysCategoryDao.findCategorys();
		for (Node node : rows) {
			System.out.println(node);
		}
	}

}
