package com.baizhi.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web")
public class WebController {
    @RequestMapping("interceptor")
    public String interceptor(Model model) {
        model.addAttribute("hello", "hello");
        System.out.println("model : " + model);
        return "inderceptor";
    }
}
