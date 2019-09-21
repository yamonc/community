package com.majiang.community.community.dto;

import lombok.Data;

/**
 * Created by Intellij IDEA.
 * User:  陈亚萌
 * Date:  2019/9/19
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;


}
