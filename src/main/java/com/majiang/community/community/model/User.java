package com.majiang.community.community.model;

import lombok.Data;

/**
 * Created by Intellij IDEA.
 * User:  陈亚萌
 * Date:  2019/9/19
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;



}
