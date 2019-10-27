package com.cyl.springboottest1.mappers;

import com.cyl.springboottest1.entity.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

public class Xmldao extends SqlSessionDaoSupport {

    public List<User> findallusers(){
        return this.getSqlSession().getMapper(com.cyl.springboottest1.mappers.XmlUserMapper.class).findallusers();
    }
}
