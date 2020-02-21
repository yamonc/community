package com.majiang.community.community.enums;

/**
 * @Created by Intellij IDEA.
 * @author: 陈亚萌
 * @Date: 2020/2/21
 */


public enum NotificationTypeEnum {
    /**
     * 当回复问题的时候需要拼接上去的字符串
     */
    REPLY_QUESTION(1,"回复了问题"),

    REPLY_COMMENT(2,"回复了评论"),
    ;
    private int Type;
    private String name;

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        this.Type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    NotificationTypeEnum(int Type, String name) {
        this.Type = Type;
        this.name = name;
    }
    public static String nameOfType(int type){
        for (NotificationTypeEnum notificationTypeEnum : NotificationTypeEnum.values()) {
            if (notificationTypeEnum.getType()==type){
                return notificationTypeEnum.getName();
            }
        }
        return "";
    }
}
