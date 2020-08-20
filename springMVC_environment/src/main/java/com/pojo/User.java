package com.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: springMVC_environment
 * @description: 用户实体类
 * @author: Su
 * @create: 2020-08-18 10:19
 **/
public class User implements Serializable {
    private String userName;
    private Integer age;
    private Date date;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", date=" + date +
                '}';
    }
}
