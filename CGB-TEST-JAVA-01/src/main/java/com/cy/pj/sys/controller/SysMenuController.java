package com.cy.pj.sys.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.service.SysMenuService;

@RestController
@RequestMapping("/menu/")
public class SysMenuController {
	
	@Autowired
	private SysMenuService sysMenuService;
	
	//更新菜单操作
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(SysMenu entity) {
		sysMenuService.updateObject(entity);
		return new JsonResult("update OK");
	}
	
	//添加菜单信息
	@RequestMapping("doSaveObject")
	public JsonResult doInsertObject(SysMenu entity) {
		sysMenuService.insertObject(entity);
		return new JsonResult("Menu save OK");
	}
	
	/**
	 * 添加信息,先查询上级菜单信息
	 * @return
	 */
	@RequestMapping("doFindZtreeMenuNodes")
	public JsonResult doFindZtreeMenuNodes() {
		return new JsonResult(sysMenuService.findZtreeMenuNodes());
	}
	
	@RequestMapping("doDeleteObject")
	public JsonResult doDeleteObject(Integer id) {
		sysMenuService.deleteObject(id);
		return new JsonResult("Menu Delete OK");
	}

	/**
	 * 根据条件查询数据
	 * @return
	 */
	@RequestMapping("doFindObjects")
	public JsonResult doFindObjects() {
		return new JsonResult(sysMenuService.findObjects());
	}
	
}
