package com.cyl.springboottest1;

import com.cyl.springboottest1.fli.Group1;
import com.cyl.springboottest1.fli.Group2;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;


public class User implements Serializable {
    private Integer id;
    @NotBlank(message = "名字不能为空",groups = {Group1.class})
    private String name;
    @NotBlank(message = "密码不能为空",groups = {Group1.class,Group2.class})
    private String pwd;

    private List<Coser> cosers;

    public List<Coser> getCosers() {
        return cosers;
    }

    public void setCosers(List<Coser> cosers) {
        this.cosers = cosers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
