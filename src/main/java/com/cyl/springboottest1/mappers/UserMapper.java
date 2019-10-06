package com.cyl.springboottest1.mappers;


import com.cyl.springboottest1.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    /**
     * 查询所有用户
     * @return
     */

    @Select("SELECT * FROM stu")
    @Results({
            @Result(id=true,property ="id",column = "id"),
            @Result(property ="name",column = "name"),
            @Result(property ="pwd",column = "pwd"),
            @Result(property = "cosers",column = "id",javaType = List.class,
                    many = @Many(select = "com.cyl.springboottest1.mappers.CoserMapper.SelectCoserByUid",fetchType = FetchType.LAZY))
    })
    public List<User> findalluser();

    /**
     * 根据用户名和密码查询用户
     * @param name
     * @param pwd
     * @return
     */

    @Select("SELECT * FROM stu where name=#{name} and pwd=#{pwd}")
    public User login(@Param("name") String name, @Param("pwd") String pwd);

    /**
     * 根据用户ID查询用户
     * @param id
     * @return
     */
    @Select("SELECT * FROM STU WHERE id = #{id}")
    public User selectUserBid(@Param("id") Integer id);


    /**
     * 更新用户密码
     * @param user
     */

    @Update("UPDATE STU SET pwd=#{user.pwd} WHERE id=#{user.id}")
    public void UpdataPwdById(@Param("user") User user);

}
