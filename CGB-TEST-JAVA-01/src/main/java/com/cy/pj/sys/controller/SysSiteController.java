package com.cy.pj.sys.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysSite;
import com.cy.pj.sys.service.SysSiteService;

@RestController
@RequestMapping("/site/")
public class SysSiteController {
	
	@Autowired
	private SysSiteService sysSiteService;
	
	@RequestMapping("doDeleteObject")
	public JsonResult doDeleteObject(Integer id) {
		sysSiteService.deleteObject(id);
		return new JsonResult("删除成功");
	}
	
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(SysSite entity) {
		sysSiteService.saveObject(entity);
		return new JsonResult("保存成功");
	}
	
	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(String name,Integer pageCurrent){
		PageObject<SysSite> sysSite = sysSiteService.findPageObjects(name, pageCurrent);
	return new JsonResult(sysSite);
	}

}
