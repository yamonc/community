package com.majiang.community.community.exception;

/**
 * @Created by Intellij IDEA.
 * @author: 陈亚萌
 * @Date: 2020/1/19
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{
    /**
     * 表示搜索问题不存在的状态
     */
    QUESTION_NOT_FOUND(2001,"你找到的问题不在了，要不要换个试试"),
    TARGET_PARENT_NOT_FOUND(2001,"未选中任何问题或者评论回复"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后操作！"),
    SYS_ERROR(2004,"服务冒烟了，请稍后重新试下！"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或者不存在！"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在") ,
    CONTENT_IS_EMPTY(2007,"输入内容不能为空！"),
    READ_NOTIFICATION_FAIL(2008,"读取别人的信息，非法操作") ,
    NOTIFICATION_NOT_FOUND(2009,"消息没有找到"),

    ;
    private Integer code;
    private String message;
    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code=code;
    }
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
