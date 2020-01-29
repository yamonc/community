package com.majiang.community.community.dto;

import com.majiang.community.community.model.User;
import lombok.Data;

/**
 * @Created by Intellij IDEA.
 * @author: 陈亚萌
 * @Date: 2020/1/29
 */
@Data
public class CommentDTO {

    private Long id;

    private Long parentId;


    private Integer type;


    private Long commentator;


    private Long gmtCreate;


    private Long gmtModified;


    private Long likeCount;

    private String content;
    private User user;

}
