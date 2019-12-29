package com.cy.pj.sys.controller;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysProject;
import com.cy.pj.sys.service.SysProjectService;

@RestController
@RequestMapping("/project/")
public class SysProjectController {
	@Autowired
	private SysProjectService sysProjectService;
	
	@RequestMapping("doInsertProject")
	public JsonResult doInsertProject(SysProject entity){
		sysProjectService.insertProject(entity);
		return new JsonResult("添加成功");
	}
	
	@RequestMapping("doFindProjectById")
	public JsonResult doFindProjectById(Integer id){
		SysProject data = sysProjectService.findProjectById(id);
		return new JsonResult(data);
	}
	
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(SysProject entity) throws UnsupportedEncodingException{
		System.out.println("controller："+entity);
		System.out.println("浏览器接收名称："+entity.getName());
		sysProjectService.updateObject(entity);
		return new JsonResult("更新成功");
	}
	
	@RequestMapping("doDeleteObjectById")
	public JsonResult doDeleteObjectById(Integer...id){
		sysProjectService.deleteObjects(id);
		return new JsonResult("删除成功");
	}
	
	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(String name,Integer pageCurrent){
		PageObject<Map<String, Object>> sys = sysProjectService.findPageObjects(name, pageCurrent);
		return new JsonResult(sys);		
	}

}
