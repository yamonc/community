package com.majiang.community.community.controller;

import com.majiang.community.community.dto.CommentDTO;
import com.majiang.community.community.dto.ResultDTO;
import com.majiang.community.community.exception.CustomizeErrorCode;
import com.majiang.community.community.mapper.CommentMapper;
import com.majiang.community.community.model.Comment;
import com.majiang.community.community.model.User;
import com.majiang.community.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Created by Intellij IDEA.
 * @author: 陈亚萌
 * @Date: 2020/1/20
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    @ResponseBody
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){
        //首先判断用户是否登录
        User user= (User) request.getSession().getAttribute("user");
        if (user==null){
            return ResultDTO.errorOf  (CustomizeErrorCode.NO_LOGIN);
        }

        Comment comment=new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setLikeCount(0L);
        comment.setCommentator(1L);
        commentService.insert(comment);
        return ResultDTO.okOf();

    }
}
