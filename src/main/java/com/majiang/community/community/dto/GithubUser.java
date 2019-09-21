package com.majiang.community.community.dto;

import lombok.Data;

/**
 * Created by Intellij IDEA.
 * User:  陈亚萌
 * Date:  2019/9/19
 */
//封装json中的user数据
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;

}
