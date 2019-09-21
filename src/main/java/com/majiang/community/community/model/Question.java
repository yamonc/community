package com.majiang.community.community.model;

import lombok.Data;

/**
 * Created by Intellij IDEA.
 * User:  陈亚萌
 * Date:  2019/9/20
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;


}
