package com.cy.pj.sys.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.entity.SysStudents;
import com.cy.pj.sys.service.SysStudentsService;

@RestController
@RequestMapping("/students/")
public class SysStudentsController {

	@Autowired
	private SysStudentsService sysStudentsService;
	
	@RequestMapping("doFindStudentById")
	public JsonResult doFindStudentById(Integer id){
		SysStudents data = sysStudentsService.findStudentById(id);
		return new JsonResult(data);
	}
	
	@RequestMapping("deleteObjects")
	public JsonResult deleteObjects(Integer...id){
		sysStudentsService.deleteObjects(id);
		return new JsonResult("删除成功");
	}
	
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(SysStudents entity) 
			throws Exception{
		sysStudentsService.updateObject(entity);
		return new JsonResult("修改成功");
	}
	
	@RequestMapping("doInsertStudent")
	public JsonResult doInsertStudent(SysStudents entity){
		sysStudentsService.insertStudent(entity);
		return new JsonResult("添加成功");
	}
	
	
	@Cacheable
	@RequestMapping("doFindPageObject")
	public JsonResult doFindPageObject(String sname,Integer pageCurrent){
		return new JsonResult(sysStudentsService.findPageObject(
						sname, pageCurrent));
	}
	
	
	
	
	
}
