package com.njp.learn.ssl.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SslController {
    private static final Logger logger = LoggerFactory.getLogger("image");
    @RequestMapping("/")
    @ResponseBody
    public String helloWorld() {
        System.out.println("hello,world");
        logger.warn("hello.world");
        return "Hello, world";
    }

}