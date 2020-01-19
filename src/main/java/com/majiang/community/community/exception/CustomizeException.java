package com.majiang.community.community.exception;

import lombok.Data;

/**
 * @Created by Intellij IDEA.
 * @author: 陈亚萌
 * @Date: 2020/1/19
 * @Description: 自定义异常类
 */
@Data
public class CustomizeException extends RuntimeException  {
    private String message;
    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message= errorCode.getMessage();
    }
    public CustomizeException(String message) {
        this.message=message;
    }
}
