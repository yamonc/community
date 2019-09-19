package com.majiang.community.community.mapper;

import com.majiang.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


/**
 * Created by Intellij IDEA.
 * User:  陈亚萌
 * Date:  2019/9/19
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified} )")
    void insert(User user);
}
