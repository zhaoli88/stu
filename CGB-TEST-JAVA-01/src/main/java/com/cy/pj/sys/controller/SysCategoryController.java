package com.cy.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.service.SysCategoryService;

@RestController
@RequestMapping("/category/")
public class SysCategoryController {
	@Autowired
	private SysCategoryService sysCategoryService;

	@RequestMapping("doFindCategory")
	public JsonResult doFindCategory(){
		return new JsonResult(sysCategoryService.findCategory());
	}
	@RequestMapping("findObjectById")
	public JsonResult findObjectById(Integer id){
		Node data = sysCategoryService.findObjectById(id);
		return new JsonResult(data);
	}
}
