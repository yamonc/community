package com.majiang.community.community.controller;

import com.majiang.community.community.dto.CommentCreateDTO;
import com.majiang.community.community.dto.CommentDTO;
import com.majiang.community.community.dto.ResultDTO;
import com.majiang.community.community.enums.CommentTypeEnum;
import com.majiang.community.community.exception.CustomizeErrorCode;
import com.majiang.community.community.model.Comment;
import com.majiang.community.community.model.User;
import com.majiang.community.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request){
        //首先判断用户是否登录
        User user= (User) request.getSession().getAttribute("user");
        if (user==null){
            return ResultDTO.errorOf  (CustomizeErrorCode.NO_LOGIN);
        }
        /*if (commentCreateDTO == null ||commentCreateDTO.getContent()==null|| "".equals(commentCreateDTO.getContent())){
            return ResultDTO.errorOf  (CustomizeErrorCode.CONTENT_IS_EMPTY);
        }*/

        if (commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())){
            return ResultDTO.errorOf  (CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment=new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setLikeCount(0L);
        comment.setCommentator(user.getId());
        commentService.insert(comment);
        return ResultDTO.okOf();

    }
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id")Long id){
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
