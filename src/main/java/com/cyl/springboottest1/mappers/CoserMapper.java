package com.cyl.springboottest1.mappers;

import com.cyl.springboottest1.Coser;
import com.cyl.springboottest1.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CoserMapper {

    @Select("select * from coser where id= #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "cname",column = "cname"),
            @Result(property = "cstr",column = "cstr"),
            @Result(property = "user",column = "uid",javaType = User.class,
                    one = @One(select = "com.cyl.springboottest1.mappers.UserMapper.selectUserBid",fetchType = FetchType.LAZY)
            )
    })
    public Coser SelectCoserByUid(Integer id);
}
