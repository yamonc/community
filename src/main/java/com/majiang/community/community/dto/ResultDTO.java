package com.majiang.community.community.dto;

import com.majiang.community.community.exception.CustomizeErrorCode;
import com.majiang.community.community.exception.CustomizeException;
import lombok.Data;

import java.sql.ResultSet;

/**
 * @Created by Intellij IDEA.
 * @author: 陈亚萌
 * @Date: 2020/1/20
 */
@Data
public class ResultDTO {
    private Integer code;
    private String message;
    public static ResultDTO errorOf (Integer code,String message){
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static Object errorOf(CustomizeErrorCode noLogin) {
        return errorOf(noLogin.getCode(),noLogin.getMessage());
    }
    public static ResultDTO okOf(){
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeException  ex) {
        return errorOf(ex.getCode(),ex.getMessage());
    }
}
