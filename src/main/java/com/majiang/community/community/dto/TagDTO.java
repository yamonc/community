package com.majiang.community.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @Created by Intellij IDEA.
 * @author: 陈亚萌
 * @Date: 2020/2/20
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
