package com.majiang.community.community.controller;

import com.majiang.community.community.mapper.QuestionMapper;
import com.majiang.community.community.mapper.UserMapper;
import com.majiang.community.community.model.Question;
import com.majiang.community.community.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Intellij IDEA.
 * User:  陈亚萌
 * Date:  2019/9/20
 */
@Controller
public class PublistController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model
    ) {

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        User user = null;
        if (title==null || title==""){
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description==null || description==""){
            model.addAttribute("error", "描述不能为空");
            return "publish";
        }
        if (tag==null || tag==""){
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        Cookie[] cookies = request.getCookies();
        if (cookies!=null&&cookies.length!=0){
            for (Cookie cookie :cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";

        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());


        questionMapper.create(question);
        return "redirect:/";
    }
}
