package com.cy.sys.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.common.vo.JsonResult;
import com.cy.common.vo.PageObject;
import com.cy.sys.dao.SysProjectDao;
import com.cy.sys.entity.SysProject;
import com.cy.sys.service.SysProjectService;

@RestController
@RequestMapping("project")
public class SysProjectController {

	@Autowired
	private SysProjectService sysProjectService;
	
	@RequestMapping("doSaveObjectByName")
	public JsonResult doSaveObjectByName(Integer userId, String filmName){
		if (userId == null) {
			userId = 1;
		}
		sysProjectService.saveObjectByProjectName(userId, filmName);
		return new JsonResult("收藏成功....(测试阶段,具体收藏到哪里还不知道)");
		
	}
	
	/**
	 * 根据项目name查询项目列表
	 * @param id
	 * @return
	 */
	@RequestMapping("doFindObjectById")
    public JsonResult doFindObjectById(Integer userId){
		if (userId == null) {
			userId = 1;
		}
		return new JsonResult(
				sysProjectService.findObjectById(userId));
    }
	
	/**
	 * 根据项目类型的分页查询
	 * @param categoryId
	 * @param pageCurrent
	 * @return
	 */
	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(Integer categoryId, Integer pageCurrent){
		PageObject<SysProject> sysFilms = sysProjectService.findObjects(categoryId, pageCurrent);
		return new JsonResult(sysFilms);
	}
	
	@RequestMapping("doFindObjectByName")
	public JsonResult doFindObjectByName(String name){
		return new JsonResult(sysProjectService.findProjectByName(name));
	}
	
}
