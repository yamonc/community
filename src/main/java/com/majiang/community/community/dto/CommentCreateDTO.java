package com.majiang.community.community.dto;

import lombok.Data;

/**
 * @Created by Intellij IDEA.
 * @author: 陈亚萌
 * @Date: 2020/1/20
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
