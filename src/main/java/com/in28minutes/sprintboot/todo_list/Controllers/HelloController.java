package com.in28minutes.sprintboot.todo_list.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {
    private Logger logger =LoggerFactory.getLogger(this.getClass());
    @RequestMapping("/")
    @ResponseBody
    public String sayHello(HttpServletRequest req){
        return "Hello this is hello controller" + req.getSession().getId();
    }

    @RequestMapping("/hello-jsp")
    @ResponseBody
    public String sayHelloJsp(@RequestParam String name){
        logger.debug("Debug log: {}", name);
        return name;
    }
}
