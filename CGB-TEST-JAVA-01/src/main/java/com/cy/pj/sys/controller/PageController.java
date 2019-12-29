package com.cy.pj.sys.controller;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 集合了登录,主页,和各个转跳的任务
 * @author 44734
 *
 */
@RequestMapping("/")
@Controller
public class PageController {
	
	/**
	 * 记录访问次数的代码
	 */
	private AtomicLong atomicLong=new AtomicLong(0);
	
	/**
	 * 共性代码封装
	 * @param moduleUI
	 * @return
	 */
	@RequestMapping("{module}/{moduleUI}")
	public String doModuleUI(@PathVariable String moduleUI) {
			return "sys/"+moduleUI;
	}

	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "common/page";
	}
	
	@RequestMapping("doLoginUI")
	public String doLoginUI(){
			return "login01";
	}
	
	@RequestMapping("doIndexUI")
	public String doIndexUI(){
		return "starter";
	}
	
	
}
