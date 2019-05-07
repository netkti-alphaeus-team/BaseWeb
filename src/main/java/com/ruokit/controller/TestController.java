package com.ruokit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping("/test.do")
	public String getTestPage(Model m) {	
		logger.debug("test.do enterance");
		m.addAttribute("context", "test message");
		return "test";
	}
}
