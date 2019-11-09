package com.cyl.springboottest1.controller;

import com.cyl.springboottest1.entity.Coser;
import com.cyl.springboottest1.entity.User;
import com.cyl.springboottest1.service.CoserServiceImp;
import com.cyl.springboottest1.service.Coserservice;
import com.cyl.springboottest1.service.UserService;
import com.cyl.springboottest1.service.UserServiceImp;
import com.cyl.springboottest1.utils.Group1;
import com.cyl.springboottest1.utils.Group2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class Controllers {
    @Autowired
    private UserService userService;
    @Autowired
    private Coserservice coserService;

    /**
     * 登录
     * @param user
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(@Validated(value = Group1.class) User user, HttpServletRequest request, HttpSession session) {
        /*if (request.getMethod().equals("GET")) {
            return "index1";
        }
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            request.setAttribute("errors", allErrors);
            System.out.println(allErrors);
            return "index1";
        }*/
        User user1 = userService.login(user);
        if (user1 == null) {
            request.setAttribute("uperror", "账号或密码错误");
            return "index1";
        }
        session.setAttribute("se_name", user1);
        return "main";
    }

    /**
     * 删除session，退出登录
     *
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        User user = (User) session.getAttribute("se_name");

        if (user != null) {
            session.removeAttribute("se_name");
            System.out.println("删除session");
        }

        return "index1";
    }

    /**
     * 查询所有用户,关联查询Coser
     */
    @RequestMapping("/users")
    public void findalluser() {
        List<User> users = userService.findusers();

        for (User user : users) {
            System.out.println(user.getName());
            System.out.println("需要时再查询coser");
            System.out.println(user.getCosers());
        }
    }
    /**
     * 更新用户密码
     *
     * @param session
     * @param request
     */
    @RequestMapping("/updatepwd")
    public String UpdatePwd(HttpSession session, HttpServletRequest request, String newpwd1) {

        if (newpwd1 != null || newpwd1.equals("")) {
            userService.UpdatePwd((User) session.getAttribute("se_name"), newpwd1);
            System.out.println("密码更新成功");

            return "main";
        }
        return "/error";
    }

    /**
     * 根据ID查询coser
     * @return
     */
    @RequestMapping("/csbid")
    public String findcoserbyid() {

        Coser coser = coserService.findcoserbyid(3);
        if (coser!=null){
            System.out.println(coser.getCname());
        }
        System.out.println("--------");
        //System.out.println(coser.getUser());
        return "index1";
    }

    /**
     * 增加
     * @param request
     * @param session
     * @param user
     * @return
     */
    @RequestMapping("/adduser")
    public String adduser(HttpServletRequest request, HttpSession session, @Validated(value = Group2.class) User user){

        int res=userService.AddUser(user);
        System.out.println(res);
        if (res!=1){
            System.out.println("注册失败");
            request.setAttribute("error","注册失败");
            return "reg";
        }
        session.setAttribute("se_name",user);
        System.out.println("注册成功");
        return "main";
    }

    /**
     * 查询用户名是否已经存在
     * @param user
     * @return
     */
    @RequestMapping("/checkname")
    @ResponseBody
    public User findUserByName(User user){

        String name= user.getName();
        User user1= userService.findUserByName(name);

        return user1;
    }

    /**
     * 上传
     * @param headimg
     * @param request
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload")
    public String upload(@RequestParam(value = "headimg") MultipartFile headimg,HttpServletRequest request,HttpSession session) throws IOException {

        String path= ResourceUtils.getFile("classpath:static").getPath();
        String filename=((User)session.getAttribute("se_name")).getName();
        String fname=headimg.getOriginalFilename();
        String type= fname.substring(fname.lastIndexOf("."));
        headimg.transferTo(new File(path+"/"+filename+type));
        return "main";
    }

    /**
     * 根据用户ID查询用户,级联查询
     * @param session
     * @return
     */
    @RequestMapping("/select")
    @ResponseBody
    public List findcoserbyuid(HttpSession session){
        User user= userService.findUserById(((User)session.getAttribute("se_name")).getId());
        System.out.println(user.getCosers());
        return user.getCosers();
    }

    /**
     * xml配置方式查询所有
     * @return
     */
    @RequestMapping("/fuxml")
    public String findalluserbyxml(){
        List list= userService.xmlmapperusers();
        System.out.println(list);
        return null;
    }

    /**
     * rabbitmq测试
     * @return
     */
    @RequestMapping("/mqtest")
    public String rabbitmqtest(){
        userService.mqtest();
        return null;
    }

}
