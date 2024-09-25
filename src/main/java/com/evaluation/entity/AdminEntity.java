package com.evaluation.entity;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "管理员实体类")
public class AdminEntity {
    private Integer userid;

    private String username;

    private String userpw;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpw() {
        return userpw;
    }

    public void setUserpw(String userpw) {
        this.userpw = userpw;
    }
}