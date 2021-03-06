package com.ruokit.main.controller.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruokit.main.service.test.TestService;

@Controller
@RequestMapping("/test")
public class TestController {

  private static final Logger logger = LoggerFactory.getLogger(TestController.class);

  @Autowired
  TestService testService;

  @RequestMapping("/test.do")
  public String getTestPage(Model m) {
    logger.info("scr. test");
    m.addAttribute("context", "test message");
    return "test";
  }

  @RequestMapping("/getTest.do")
  public String getTest(Model m) {
    String result = testService.getTest();
    m.addAttribute("context", result);
    return "test";
  }

  @RequestMapping("/setTest.do")
  public String setTest(Model m) {
    String result = testService.saveTest();
    m.addAttribute("context", result);
    return "test";
  }
}
