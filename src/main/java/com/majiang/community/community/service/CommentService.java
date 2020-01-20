package com.majiang.community.community.service;

import com.majiang.community.community.exception.CustomizeErrorCode;
import com.majiang.community.community.exception.CustomizeException;
import com.majiang.community.community.model.Comment;
import org.springframework.stereotype.Service;

/**
 * @Created by Intellij IDEA.
 * @author: 陈亚萌
 * @Date: 2020/1/20
 */
@Service
public class CommentService {
    public void insert(Comment comment) {
        if(comment.getParentId()==null || comment.getParentId()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARENT_NOT_FOUND);
        }
    }
}
