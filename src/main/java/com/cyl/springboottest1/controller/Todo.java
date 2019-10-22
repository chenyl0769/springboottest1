package com.cyl.springboottest1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Todo {
    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping("/index")
    public String aa() {
        return "index1";
    }

    /**
     * 跳转到密码修改页面
     * @return
     */
    @RequestMapping("/toupdatepwd")
    public String ToUpdtePwd() {
        return "edit";
    }

    /**
     * 跳转到主页页面
     * @return
     */
    @RequestMapping("/toreg")
    public String toreg() {

        return "reg" ;
    }
}
