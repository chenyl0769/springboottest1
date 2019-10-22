package com.cyl.springboottest1.mappers;


import com.cyl.springboottest1.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "user",key ="#a0")
    @Select("SELECT * FROM stu where name=#{name} and pwd=#{pwd}")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(property = "cosers",column = "id",javaType = List.class,
                    many = @Many(select = "com.cyl.springboottest1.mappers.CoserMapper.SelectCoserByUid",fetchType = FetchType.LAZY))
    })
    public User login(@Param("name") String name, @Param("pwd") String pwd);

    /**
     * 根据用户ID查询用户
     * @param id
     * @return
     */
    @Select("SELECT * FROM STU WHERE id = #{id}")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(property = "cosers",column = "id",javaType = List.class,
            many = @Many(select = "com.cyl.springboottest1.mappers.CoserMapper.findcoserbyuid",fetchType = FetchType.LAZY))
    })
    public User findUserById(@Param("id") Integer id);


    /**
     * 根据用户名查询用户
     * @param name
     * @return
     */
    @Select("SELECT * FROM STU WHERE name = #{name}")
    public User selectUserByName(@Param("name") String name);


    /**
     * 更新用户密码
     * @param user
     */

    @Update("UPDATE STU SET pwd=#{user.pwd} WHERE id=#{user.id}")
    public void UpdataPwdById(@Param("user") User user);


    /**
     * 添加用户，并返回插入的ID
     * @param user
     * @return
     */
    @Insert("INSERT INTO stu(name,pwd) VALUE(#{user.name},#{user.pwd}) ")
    @SelectKey(statement = "select last_insert_id()",keyColumn = "id",before = false,resultType = int.class,keyProperty = "user.id")
    public int AddUser(@Param("user")User user);

}
