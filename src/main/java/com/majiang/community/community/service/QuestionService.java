package com.majiang.community.community.service;


import com.majiang.community.community.dto.PaginationDTO;
import com.majiang.community.community.dto.QuestionDTO;
import com.majiang.community.community.mapper.QuestionMapper;
import com.majiang.community.community.mapper.UserMapper;
import com.majiang.community.community.model.Question;
import com.majiang.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Intellij IDEA.
 * User:  陈亚萌
 * Date:  2019/9/21
 */
//可以是用question和usermapper，相当于中间层，为service
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO= new PaginationDTO();

        Integer totalPage;
        Integer totalCount = questionMapper.count();


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
        paginationDTO.setPagenation(totalPage,page);
        Integer offset=size*(page-1);

        List<Question> questions = questionMapper.list(offset,size);

        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : questions) {
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //将question的值copy到questionDTO中
            questionDTO.setGmtCreate(question.getGmtCreate());
            BeanUtils.copyProperties(question,questionDTO);

            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(userId);
        PaginationDTO paginationDTO= new PaginationDTO();

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

        List<Question> questions = questionMapper.listByUserId(userId,offset,size);

        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : questions) {
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //将question的值copy到questionDTO中
            questionDTO.setGmtCreate(question.getGmtCreate());
            BeanUtils.copyProperties(question,questionDTO);

            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }
}
