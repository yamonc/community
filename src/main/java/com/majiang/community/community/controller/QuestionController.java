package com.majiang.community.community.controller;

import com.majiang.community.community.dto.QuestionDTO;
import com.majiang.community.community.mapper.QuestionExtMapper;
import com.majiang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Intellij IDEA.
 * User:  陈亚萌
 * Date:  2019/9/22
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;


    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Long id,
                           Model model){
        //通过前台的获取到的id，获取数据库的数据
        QuestionDTO questionDTO=questionService.getById(id);
        //增加阅读数
        questionService.incView(id);
        //需要将这个结果传输到页面上去
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
