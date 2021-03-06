package com.majiang.community.community.service;

import com.majiang.community.community.dto.NotificationDTO;
import com.majiang.community.community.dto.PaginationDTO;
import com.majiang.community.community.enums.NotificationStatusEnum;
import com.majiang.community.community.enums.NotificationTypeEnum;
import com.majiang.community.community.exception.CustomizeErrorCode;
import com.majiang.community.community.exception.CustomizeException;
import com.majiang.community.community.mapper.NotificationMapper;
import com.majiang.community.community.mapper.UserMapper;
import com.majiang.community.community.model.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Created by Intellij IDEA.
 * @author: 陈亚萌
 * @Date: 2020/2/21
 */
@Service
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private UserMapper userMapper;
    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO<NotificationDTO> paginationDTO= new PaginationDTO<>();
        Integer totalPage;
        NotificationExample notificationExample=new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(userId);
        Integer totalCount =(int) notificationMapper.countByExample(notificationExample);

        if (totalCount%size==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size+1;
        }
        //size*（page-1）
        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }
        //综述
        paginationDTO.setPagenation(totalPage,page);

        Integer offset=size*(page-1);
        NotificationExample example=new NotificationExample();
        example.createCriteria().andReceiverEqualTo(userId);
        example.setOrderByClause("gmt_create desc");
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example,new RowBounds(offset, size));




        if(notifications.size()==0){
            return paginationDTO;
        }

        List<NotificationDTO> notificationDTOS=new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification,notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }
        paginationDTO.setData(notificationDTOS);
        return paginationDTO;
    }

    public Long unreadCount(Long userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(userId)
        .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if (notification==null){
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }if (!notification.getReceiver().equals(user.getId())){
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        //更新已读
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);
        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification,notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }
}
