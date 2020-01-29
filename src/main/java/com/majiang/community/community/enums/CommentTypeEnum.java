package com.majiang.community.community.enums;

import lombok.Data;

/**
 * @Created by Intellij IDEA.
 * @author: 陈亚萌
 * @Date: 2020/1/20
 */

public enum  CommentTypeEnum {
    /**
     * 用来判断是提问题还是进行问题下的评论
     */
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if (value.getType().equals(type)){
                 return true;
            }

        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }
}
