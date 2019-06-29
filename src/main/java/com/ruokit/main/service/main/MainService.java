package com.ruokit.main.service.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MainService {

  private static final Logger logger = LoggerFactory.getLogger(MainService.class);

  public String getMainPage() {
    logger.info("srv. getMainPage");
    return "main";
  }
}
