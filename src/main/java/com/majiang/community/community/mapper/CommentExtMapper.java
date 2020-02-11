package com.majiang.community.community.mapper;

import com.majiang.community.community.model.Comment;
import com.majiang.community.community.model.CommentExample;
import com.majiang.community.community.model.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExtMapper {
    int intCommentCount(Comment comment);
}