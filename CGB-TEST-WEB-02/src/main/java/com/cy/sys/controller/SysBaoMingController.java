package com.cy.sys.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cy.common.vo.JsonResult;
import com.cy.sys.entity.SysBaoMing;
import com.cy.sys.service.SysBaoMingService;
/**
 * 报名用的
 * @author 44734
 *
 */

@RestController
@RequestMapping("/baoming/")
public class SysBaoMingController {

	@Autowired
	private SysBaoMingService sysBaoMingService;
	
	@RequestMapping("doInsertstudent")
   public JsonResult insertObject(SysBaoMing entity) {
	   sysBaoMingService.insertObject(entity);
	return new JsonResult("报名成功");
	   
   }
}
