package com.cy.pj.sys.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.entity.SysCoach;
import com.cy.pj.sys.service.SysCoachService;

@RestController
@RequestMapping("/coach/")
public class SysCoachController {
	@Autowired
	private SysCoachService sysCoachService;
	
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(SysCoach entity){
		sysCoachService.updateObject(entity);
		return new JsonResult("修改成功");
	}
	
	@RequestMapping("doInsertCoach")
	public JsonResult doInsertCoach(SysCoach entity){
		sysCoachService.insertCoach(entity);
		return new JsonResult("添加成功");
	}
	
	@RequestMapping("doFindCoachById")
	public JsonResult doFindCoachById(Integer id){
		return new JsonResult(sysCoachService.findCoachById(id));
	}
	
	@RequestMapping("doDeleteObject")
	public JsonResult doDeleteObject(Integer id){
		sysCoachService.deleteObject(id);
		return new JsonResult("delete ok");
	}
	
	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(String cname,Integer pageCurrent){
		return new JsonResult(sysCoachService.findPageObjects(cname, pageCurrent));
	}

}
