package com.cyl.springboottest1;

import com.cyl.springboottest1.fli.Group1;
import com.cyl.springboottest1.service.CoserService;
import com.cyl.springboottest1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class test {
    @Autowired
    private UserService userService;
    @Autowired
    private CoserService coserService;

    @RequestMapping("/index")
    public String aa() {
        return "index1";
    }

    @RequestMapping("/login")
    public String login(@Validated(value = Group1.class) User user, BindingResult result, HttpServletRequest request, HttpSession session) {
        /*if (result.hasErrors()){
            result.getAllErrors().forEach((error)->{
                FieldError fieldError = (FieldError) error;
                // 属性
                String field = fieldError.getField();
                // 错误信息
                String message = fieldError.getDefaultMessage();
                System.out.println(field + ":" + message);
                request.setAttribute("err",message);

            });
            return "index1";
        }*/
        System.out.println(request.getMethod());
        if (request.getMethod().equals("GET")) {
            return "index1";
        }
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            request.setAttribute("errors", allErrors);
            System.out.println(allErrors);
            return "index1";
        }
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

        for (User user:users){
            System.out.println(user.getName());
            System.out.println("需要时再查询coser");
            System.out.println(user.getCosers());
        }
    }
    @RequestMapping("/toupdatepwd")
    public String ToUpdtePwd(){
        return "edit";
    }

    /**
     * 更新用户密码
     * @param session
     * @param request
     */
    @RequestMapping("/updatepwd")
    public String UpdatePwd(HttpSession session,HttpServletRequest request,String newpwd1){

        if (newpwd1!=null||newpwd1.equals("")){
            userService.UpdatePwd((User) session.getAttribute("se_name"),newpwd1);
            System.out.println("密码更新成功");

            return "main";
        }
        return "/error";
    }

    @RequestMapping("/csbid")
    public String findcoserbyid(){

        Coser coser= coserService.findcoserbyid(1);
        System.out.println(coser.getCname());
        System.out.println("--------");
        //System.out.println(coser.getUser());
        return "index1";
    }

}
