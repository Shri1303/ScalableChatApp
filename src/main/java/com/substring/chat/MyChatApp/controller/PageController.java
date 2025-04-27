package com.substring.chat.MyChatApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/test")
    public String user(){
        System.out.println("func lodiang");
        return "home";
    }

    @RequestMapping("/")
    public String index(){
        System.out.println("func lodiang");
        return "main";
    }
}
