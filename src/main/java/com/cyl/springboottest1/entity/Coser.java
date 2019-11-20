package com.cyl.springboottest1.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Objects;

/**
 * Coser实体类
 */
@JsonIgnoreProperties(value = {"handler"})
public class Coser implements Serializable {
    private Integer id;
    private Integer uid;
    private String cname;
    private String cstr;
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCstr() {
        return cstr;
    }

    public void setCstr(String cstr) {
        this.cstr = cstr;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Coser{" +
                "id=" + id +
                ", cname='" + cname + '\'' +
                ", cstr='" + cstr + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coser coser = (Coser) o;
        return Objects.equals(id, coser.id) &&
                Objects.equals(uid, coser.uid) &&
                Objects.equals(cname, coser.cname) &&
                Objects.equals(cstr, coser.cstr) &&
                Objects.equals(user, coser.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uid, cname, cstr, user);
    }
}
