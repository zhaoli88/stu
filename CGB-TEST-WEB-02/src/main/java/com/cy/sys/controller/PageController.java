package com.cy.sys.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.cy.sys.entity.Regist;
import com.cy.sys.service.RegistService;

@Controller
@RequestMapping("/")
public class PageController {
	
	@Autowired
	private RegistService registService;
	
	//注册操作
	@RequestMapping(value="doSaveObject", method=RequestMethod.POST)
	public String doSaveObject(@RequestParam("type") String type,
			@RequestParam("sname") String sname,
			@RequestParam("gender") String gender,
			@RequestParam("age") Integer age,
			@RequestParam("nember") Integer nember,
			@RequestParam("home") String home,
			@RequestParam("tel") Integer tel,
			@RequestParam("email") String email,
			@RequestParam("place") String place,
			Regist entity){
		entity.setType(type);
		entity.setSname(sname);
		entity.setGender(gender);
		entity.setAge(age);
		entity.setNember(nember);
		entity.setHome(home);
		entity.setTel(tel);
		entity.setEmail(email);
		entity.setPlace(place);

		System.out.println(entity.getSname());
		registService.saveRegistObject(entity);
		System.out.println("保存完成");
		return "test";
	}
	
	/*
	 * @RequestMapping("doSignupUI") public String doSignupUI() { return "signup"; }
	 */
	
	@RequestMapping("doTest")
	public String doTest(){
		return "doSaveObject";
	}
	
	
	
	@RequestMapping("doCategoryPageUI")
	public String doCategoryPageUI() {
		return "film/page";
	}
	
	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		return "index";
	}
	
	@RequestMapping("doDetailUI")
	public String doDetailUI() {
		return "film/detail";
	}
	@RequestMapping("doBaoMingUI")
	public String doBaoMingUI() {
		return "film/baoming";
	}
	
}
