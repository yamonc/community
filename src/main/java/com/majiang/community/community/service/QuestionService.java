package com.majiang.community.community.service;


import com.majiang.community.community.dto.PaginationDTO;
import com.majiang.community.community.dto.QuestionDTO;
import com.majiang.community.community.exception.CustomizeErrorCode;
import com.majiang.community.community.exception.CustomizeException;
import com.majiang.community.community.mapper.QuestionMapper;
import com.majiang.community.community.mapper.UserMapper;
import com.majiang.community.community.model.Question;
import com.majiang.community.community.model.QuestionExample;
import com.majiang.community.community.model.User;
import org.apache.ibatis.session.RowBounds;
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

        Integer totalCount =(int) questionMapper.countByExample(new QuestionExample());


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

        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : questions) {
            User user=userMapper.selectByPrimaryKey(question.getCreator());
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
        PaginationDTO paginationDTO= new PaginationDTO();
        Integer totalPage;
        QuestionExample questionExample=new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount =(int) questionMapper.countByExample(questionExample);

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
        QuestionExample example=new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example,new RowBounds(offset, size));


        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : questions) {
            User user=userMapper.selectByPrimaryKey(question.getCreator());
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
    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        // 如果问题不存在，则进行抛出异常进行处理，处理异常直接抛出到advice包下的CustomizeExceptionHandler类中的拦截器中
        if(question==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);


        User user=userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        //判断
        if (question.getId()==null){
            //如果等于null证明是第一次创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else{
            //更新
            Question updateQuestion=new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example=new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int update = questionMapper.updateByExampleSelective(updateQuestion,example);
            if (update != 1){
                //没有进行更新，或者更新失败
                throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }
}
