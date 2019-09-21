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
        Integer totalCount = questionMapper.count();
        PaginationDTO paginationDTO= new PaginationDTO();

        //综述
        paginationDTO.setPagenation(totalCount,page,size);
        //size*（page-1）
        if (page<1){
            page=1;
        }
        if (page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }
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
}
