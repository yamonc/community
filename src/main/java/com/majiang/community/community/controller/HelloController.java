package com.majiang.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Intellij IDEA.
 * User:  陈亚萌
 * Date:  2019/9/18
 */
@Controller
public class HelloController {
    @GetMapping("/")
    public String hello(@RequestParam(name="name",required=false, defaultValue="World") String name, Model model){
        model.addAttribute("name",name);
        return "index";
    }

}
