package com.cy.pj.sys.service;
import java.util.List;
import com.cy.pj.common.vo.Node;

public interface SysCategoryService {

	List<Node> findCategory();
	Node findObjectById(Integer id);
}
