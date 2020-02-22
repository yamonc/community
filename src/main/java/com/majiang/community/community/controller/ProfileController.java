package com.majiang.community.community.controller;

import com.majiang.community.community.dto.PaginationDTO;
import com.majiang.community.community.mapper.UserMapper;
import com.majiang.community.community.model.User;
import com.majiang.community.community.service.NotificationService;
import com.majiang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Intellij IDEA.
 * User:  陈亚萌
 * Date:  2019/9/21
 */
@Controller
public class ProfileController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action,Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page",defaultValue = "1")Integer page,
                          @RequestParam(name="size",defaultValue = "5")Integer size){
        User user =(User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/";
        }
        if ("question".equals(action)){
            model.addAttribute("section","question");
            model.addAttribute("sectionName","我的提问");
            PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
            model.addAttribute("pagination",paginationDTO);
        }else if ("replies".equals(action)){
            PaginationDTO paginationDTO = notificationService.list(user.getId(),page,size);
            model.addAttribute("section","replies");
            model.addAttribute("pagination",paginationDTO);
            model.addAttribute("sectionName","最新回复");
        }

        return "profile";
    }

}
