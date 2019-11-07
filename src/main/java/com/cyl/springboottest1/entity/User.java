package com.cyl.springboottest1.entity;

import com.cyl.springboottest1.utils.Group1;
import com.cyl.springboottest1.utils.Group2;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * User实体类
 */
@JsonIgnoreProperties(value = {"handler"})
public class User implements Serializable {
    private Integer id;
    @NotBlank(message = "{user.name}",groups = {Group1.class})
    private String name;
    @NotBlank(message = "{user.pwd}",groups = {Group1.class,Group2.class})
    @Size(message = "密码需大于3",min = 4,groups ={Group2.class} )
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
