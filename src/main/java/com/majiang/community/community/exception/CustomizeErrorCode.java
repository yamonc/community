package com.majiang.community.community.exception;

import lombok.Data;

/**
 * @Created by Intellij IDEA.
 * @author: 陈亚萌
 * @Date: 2020/1/19
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{
    /**
     * 表示搜索问题不存在的状态
     */
    QUESTION_NOT_FOUND("你找到的问题不在了，要不要换个试试");
    private String message;
    CustomizeErrorCode(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }

}
