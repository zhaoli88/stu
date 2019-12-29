package com.cy.pj.sys.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;

@RestController
@RequestMapping("/log/")
public class SysLogController {

	@Autowired
	private SysLogService sysLogService;
	
	@RequestMapping("doDeleteObjects")
	public JsonResult doDeleteLogsById(Integer... ids){
		sysLogService.deleteLogsById(ids);
		return new JsonResult("删除成功");
	}
	
	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(String name,Integer pageCurrent){
		PageObject<SysLog> data=
				sysLogService.findPageObjects(name,pageCurrent);
		return new JsonResult(data);
	}
	
}
