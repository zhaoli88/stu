package com.cy.sys.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cy.sys.service.RegistService;

@Controller
public class RegistController {

	@Autowired
	private RegistService registService;

}
