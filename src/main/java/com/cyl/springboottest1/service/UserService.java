package com.cyl.springboottest1.service;

import com.cyl.springboottest1.entity.User;
import com.cyl.springboottest1.mappers.UserMapper;
import com.cyl.springboottest1.mappers.XmlUserMapper;
import com.cyl.springboottest1.mappers.Xmldao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private XmlUserMapper xmlUserMapper;

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
    //@Cacheable(value = "user",key ="#user.name")
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

    /**
     * 添加用户
     * @param user
     * @return
     */
    @Transactional
    public int AddUser(User user){
        int aa= -1;
        User user1= userMapper.selectUserByName(user.getName());
        if (user1==null){
            aa= userMapper.AddUser(user);
        }

        return aa;
    }

    /**
     * ajax验证用户名是否存在
     * @param name
     * @return
     */
    public User findUserByName(String name){
        User user1= userMapper.selectUserByName(name);
        return user1;
    }

    public User findUserById(int id){
        User user = userMapper.findUserById(id);
        return user;
    }

    public List<User> xmlmapperusers(){
        return xmlUserMapper.findallusers();
    }
}
