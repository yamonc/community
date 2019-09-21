package com.majiang.community.community.dto;

import lombok.Data;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Intellij IDEA.
 * User:  陈亚萌
 * Date:  2019/9/21
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;



    private Integer page;

    private List<Integer> pages=new ArrayList<>();

    private Integer totalPage;

    public void setPagenation(Integer totalCount, Integer page, Integer size) {

        if (totalCount%size==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size+1;
        }
        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }
        this.page=page;
        pages.add(page);
        for (int i=1;i<=3;i++){
            if (page-i>0){
                pages.add(0,page-i);
            }

            if (page+i<=totalPage){
                pages.add(page+i);
            }
        }
        //判断没有上一页
        if (page==1){
            showPrevious=true;
        }else{
            showPrevious=false;
        }

        //判断是否展示下一页
        if(page==totalPage){
            showNext=false;
        }else{
            showNext=true;
        }

        //是否展示第一页
        if (pages.contains(1)){
            showFirstPage=false;
        }else{
            showFirstPage=true;
        }
        //是否展示最后一页
        if (pages.contains(totalPage)){
            showEndPage=false;
        }else{
            showEndPage=true;
        }
    }
}
