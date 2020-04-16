package com.zhm.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author zhm
 * @date 2020/4/15 12:55
 */
@TableName("person")
public class Person {
    @TableId(value = "personid",type = IdType.AUTO)
    private Integer personid;
    @TableField("personname")
    private String personname;
    @TableField("personage")
    private Integer personage;

    public Person(Integer personid, String persnname, Integer personage) {
        this.personid = personid;
        this.personname = persnname;
        this.personage = personage;
    }

    public Person() {
    }

    public Integer getPersonid() {
        return personid;
    }

    public void setPersonid(Integer personid) {
        this.personid = personid;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersnoname(String persnname) {
        this.personname = persnname;
    }

    public Integer getPersonage() {
        return personage;
    }

    public void setPersonage(Integer personage) {
        this.personage = personage;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personid=" + personid +
                ", personname='" + personname + '\'' +
                ", personage=" + personage +
                '}';
    }
}
