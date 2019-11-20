package com.cyl.springboottest1.service;

import com.cyl.springboottest1.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
public interface UserService {
    /**
     * 查询所有用户
     * @return
     */
    public List<User> findusers();

    /**
     * 登录验证
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 增加
     * @param user
     * @return
     */
    public int AddUser(User user);

    /**
     * ajax验证用户名是否存在
     * @param name
     * @return
     */
    public User findUserByName(String name);

    /**
     * 更新
     * @param user
     * @param pwd
     */
    public void UpdatePwd(User user,String pwd);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public User findUserById(int id);

    /**
     * xml方式查询所有
     * @return
     */
    public List<User> xmlmapperusers();

    /**
     * rabbitmq测试
     */
    public void mqtest();
}
