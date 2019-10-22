package com.cyl.springboottest1.controller;

import com.cyl.springboottest1.entity.Coser;
import com.cyl.springboottest1.entity.User;
import com.cyl.springboottest1.fli.Group1;
import com.cyl.springboottest1.fli.Group2;
import com.cyl.springboottest1.service.CoserService;
import com.cyl.springboottest1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class Controllers {
    @Autowired
    private UserService userService;
    @Autowired
    private CoserService coserService;

    @RequestMapping(value = "/login")
    public String login( @Validated(value = Group1.class) User user,  HttpServletRequest request, HttpSession session) {
        if (request.getMethod().equals("GET")) {
            return "index1";
        }
        /*if (result.hasErrors()) {
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

    @RequestMapping("/csbid")
    public String findcoserbyid() {

        Coser coser = coserService.findcoserbyid(1);
        System.out.println(coser.getCname());
        System.out.println("--------");
        //System.out.println(coser.getUser());
        return "index1";
    }
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
    @RequestMapping("/upload")
    public String upload(@RequestParam(value = "headimg") MultipartFile headimg,HttpServletRequest request,HttpSession session) throws IOException {

        String path= ResourceUtils.getFile("classpath:static").getPath();
        String filename=((User)session.getAttribute("se_name")).getName();
        String fname=headimg.getOriginalFilename();
        String type= fname.substring(fname.lastIndexOf("."));
        headimg.transferTo(new File(path+"/"+filename+type));
        return "main";
    }
    @RequestMapping("/select")
    @ResponseBody
    public List findcoserbyuid(HttpSession session){
        User user= userService.findUserById(((User)session.getAttribute("se_name")).getId());
        System.out.println(user.getCosers());
        return user.getCosers();
    }

}
