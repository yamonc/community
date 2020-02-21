package com.majiang.community.community.enums;

/**
 * @Created by Intellij IDEA.
 * @author: 陈亚萌
 * @Date: 2020/2/21
 */
public enum NotificationStatusEnum {
    /**
     * 判断是否读取通知
     */
    UNREAD(0),READ(1)
    ;


    private int status;
    public int getStatus(){
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
