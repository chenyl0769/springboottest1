package com.cyl.springboottest1.service;

import com.cyl.springboottest1.User;
import com.cyl.springboottest1.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询所有用户
     * @return
     */
    public List<User> findusers(){
        return userMapper.findalluser();
    }

    /**
     * 登录验证
     * @param user
     * @return
     */
    @Cacheable(value = "user",key ="#user.name")
    public User login(User user){
        User user1=userMapper.login(user.getName(),user.getPwd());
        return user1;
    }

    /**
     * 更新用户密码
     * @param user
     */
    @CacheEvict(value = "user",key ="#user.name")
    public void UpdatePwd(User user,String pwd){
        user.setPwd(pwd);
        userMapper.UpdataPwdById(user);
    }
}
