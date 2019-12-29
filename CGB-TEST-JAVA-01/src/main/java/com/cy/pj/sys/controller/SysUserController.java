package com.cy.pj.sys.controller;
import java.util.Map;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.util.ShiroUtils;
import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysUserService;

@RestController
@RequestMapping("/user/")
public class SysUserController {
	
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("doLogin")
	public JsonResult doLogin( boolean isRememberMe,
			        String username,String password) {
		//1.获取subject对象
		Subject subject = SecurityUtils.getSubject();
		//2.通过subject提交用户信息,交给shiro框架进行认证操作
		//2.1对用户进行封装
		UsernamePasswordToken token =
				new UsernamePasswordToken(username,password);
		//加入rememberMe的相关操作
		if(isRememberMe) {
			token.setRememberMe(true);
		}
		//2.2对用户信息进行认证
		subject.login(token);
		//3.认证管理器会将token传递给realm
		return new JsonResult("登录成功");
	}
	
	@RequestMapping("isExists")
	public JsonResult doIsExists(String columnName, String columnValue) {
		int rows = sysUserService.isExist(columnName, columnValue);
		
		return new JsonResult(rows+"操作成功233(模板)");
		
	}
	
	@RequestMapping("doUpdatePassword")
	 public JsonResult doUpdatePassword(
			 String pwd,
			 String newPwd,
			 String cfgPwd) {
		 sysUserService.updatePassword(pwd, newPwd, cfgPwd);
		 return new JsonResult("修改密码成功");
	 }
	
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(SysUser entity, Integer[] roleIds) {
		sysUserService.updateObject(entity, roleIds);
		return new JsonResult("Update OK");
	}
	
	@RequestMapping("doFindObjectById")
	public JsonResult doFindObjectById(Integer id) {
		Map<String, Object> map = 
				sysUserService.findObejctById(id);
		return new JsonResult(map);
	}
	
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(SysUser entity,
			                       Integer[] roleIds) {
		sysUserService.saveObject(entity, roleIds);		
		return new JsonResult("Save OK");
		
	}
	
	//为什么选"admin"???做完登录后就改
	@RequestMapping("doValidById")
	public JsonResult doValidById(Integer id,Integer valid) {
		sysUserService.validById(id, valid, ShiroUtils.getUsername());
		return new JsonResult("操作成功");
	}
	
	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(String username,
			                            Integer pageCurrent) {
			 return new JsonResult(
				sysUserService.findPageObjects(username,
						                       pageCurrent));
		 }

}
