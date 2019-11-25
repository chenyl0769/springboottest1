package com.cyl.springboottest1.entity;

import com.cyl.springboottest1.utils.GroupLogin;
import com.cyl.springboottest1.utils.GroupRegiste;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginVO {
    @NotBlank(message = "{login.code}",groups = {GroupLogin.class})
    private String code;
    @Valid
    private User user;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
