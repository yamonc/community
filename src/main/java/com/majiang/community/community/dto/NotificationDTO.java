package com.majiang.community.community.dto;

import com.majiang.community.community.model.User;
import lombok.Data;

/**
 * @Created by Intellij IDEA.
 * @author: 陈亚萌
 * @Date: 2020/2/21
 */
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    /**
     * 通知的人
     */
    private Long notifier;
    private String notifierName;
    /**
     * 外部名称，就是现实标题
     */

    private String outerTitle;

    private String typeName;
    private Integer type;
    private Long outerid;


}
