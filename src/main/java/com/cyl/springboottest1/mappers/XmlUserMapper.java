package com.cyl.springboottest1.mappers;

import com.cyl.springboottest1.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface XmlUserMapper {

    List<User> findallusers();
}
