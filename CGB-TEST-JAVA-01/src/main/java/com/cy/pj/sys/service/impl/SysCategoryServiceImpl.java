package com.cy.pj.sys.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.dao.SysCategoryDao;
import com.cy.pj.sys.service.SysCategoryService;
@Service
public class SysCategoryServiceImpl implements SysCategoryService{

	@Autowired
	private SysCategoryDao sysCategoryDao;
	
	@Override
	public List<Node> findCategory() {
		return sysCategoryDao.findCategorys();
	}

	@Override
	public Node findObjectById(Integer id) {
		return sysCategoryDao.findObjectById(id);
	}

}
