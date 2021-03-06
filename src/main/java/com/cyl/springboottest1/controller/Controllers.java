package com.cyl.springboottest1.controller;

import com.cyl.springboottest1.entity.Coser;
import com.cyl.springboottest1.entity.LoginVO;
import com.cyl.springboottest1.entity.User;
import com.cyl.springboottest1.service.Coserservice;
import com.cyl.springboottest1.service.UserService;
import com.cyl.springboottest1.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
public class Controllers {
    @Autowired
    private UserService userService;
    @Autowired
    private Coserservice coserService;

    /**
     * 登录
     * @param loginVO
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(@Validated(value = GroupLogin.class) LoginVO loginVO, BindingResult result, HttpServletRequest request, HttpSession session) {
        //过滤get请求
        if (request.getMethod().equals("GET")) {
            return "index1";
        }
        //参数校验
        if (result.hasErrors()) {
            MyErrorValidated.returnerror(result,request);
            return "index1";
        }
        //校验验证码
        String code = loginVO.getCode();
        String sessioncode=(String) request.getSession().getAttribute("code");
        if (!code.toUpperCase().equals(sessioncode.toUpperCase())) {
            request.setAttribute("uperror", "验证码错误");
            return "index1";
        }
        //校验用户
        User user1 = userService.login(loginVO.getUser());
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
            //删除session
            session.removeAttribute("se_name");
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
    public String UpdatePwd(HttpSession session, HttpServletRequest request, @Validated(value = GroupUptatePwd.class) User user, BindingResult result) {
        //参数校验
        if (result.hasErrors()) {
            MyErrorValidated.returnerror(result,request);
            return "edit";
        }
        //更新密码
        if (user.getPwd() != null || user.getPwd().equals("")) {
            userService.UpdatePwd((User) session.getAttribute("se_name"), user.getPwd());
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
    public String adduser(HttpServletRequest request, HttpSession session, @Validated(value = GroupRegiste.class) User user, BindingResult result){
        //参数校验
        if (result.hasErrors()) {
            MyErrorValidated.returnerror(result,request);
            return "reg";
        }
        int res=userService.AddUser(user);
        if (res!=1){
            //注册失败
            request.setAttribute("error","注册失败");
            return "reg";
        }
        session.setAttribute("se_name",user);
        //注册成功
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

    @RequestMapping("/img")
    public void imgtest(HttpServletRequest request,HttpServletResponse response) throws IOException {
        RandomCode randomCode =new RandomCode();
        OutputStream outputStream=response.getOutputStream();
        BufferedImage bufferedImage=randomCode.createcode();
        request.getSession().setAttribute("code",randomCode.getCode());
        ImageIO.write(bufferedImage,"jpeg",outputStream);
        //return null;
    }

}
