package com.cy.pj.sys.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;

@RestController
@RequestMapping("/role/")
public class SysRoleController {
	@Autowired
	private SysRoleService sysRoleService;
	
	@RequestMapping("doFindRoles")
	public JsonResult doFindRoles() {
		return new JsonResult(sysRoleService.findObjects());
	}
	
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(SysRole entity,
			                         String menuIds) {
		return new JsonResult("Update OK");
	}
	
	@RequestMapping("doFindObjectById")
	public JsonResult doFindObjectById(Integer id) {
		return new JsonResult(
				sysRoleService.findObjectById(id));
	}
	
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(SysRole entity,
			                       Integer[] menuIds) {
		sysRoleService.saveObject(entity, menuIds);
		return new JsonResult("save OK");
	}
	
	@RequestMapping("doDeleteObject")
	public JsonResult doDeleteObject(Integer id) {
        sysRoleService.deleteObject(id);		
		return new JsonResult("delete OK");
	}
	
	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(String name,
			                            Integer pageCurrent) {
		return new JsonResult(
				sysRoleService.findPageObjects(name, 
						                       pageCurrent));
	}

}
