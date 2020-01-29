package com.majiang.community.community.controller;

import com.majiang.community.community.dto.CommentCreateDTO;
import com.majiang.community.community.dto.CommentDTO;
import com.majiang.community.community.dto.QuestionDTO;
import com.majiang.community.community.service.CommentService;
import com.majiang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * User:  陈亚萌
 * Date:  2019/9/22
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;


    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Long id,
                           Model model){
        //通过前台的获取到的id，获取数据库的数据
        QuestionDTO questionDTO=questionService.getById(id);
        //返回所有品论

        List<CommentDTO> commentCreateDTOList =commentService.listByQuestionId(id);
        //增加阅读数
        questionService.incView(id);


        //需要将这个结果传输到页面上去
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",commentCreateDTOList);
        return "question";
    }
}
