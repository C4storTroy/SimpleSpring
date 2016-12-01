package org.home.accounts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

    @RequestMapping("helloworld")
    public String execute(){
        System.out.println("Executing a logic spring");
        return "ok";
    }
}
