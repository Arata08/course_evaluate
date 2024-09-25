package com.evaluation.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@ApiModel(value = "学生实体类")
public class StudentEntity {

    @ApiModelProperty(value = "用户ID", name = "stuId", example = "2", required = true)
    private Integer stuId;

    @ApiModelProperty(value = "学号", name = "stuXuehao", example = "0002", required = true)
    private String stuXuehao;

    @ApiModelProperty(value = "姓名", name = "stuRealname", example = "lx", required = true)
    private String stuRealname;

    @ApiModelProperty(value = "性别", name = "stuSex", example = "123456", required = true)
    private String stuSex;

    @ApiModelProperty(value = "年龄", name = "stuAge", example = "123456", required = true)
    private String stuAge;

    @ApiModelProperty(value = "xue号", name = "stuCard", example = "90293", required = true)
    private String stuCard;

    @ApiModelProperty(value = "政治面貌", name = "stuZhengzhimianmao", example = "123456", required = true)
    private String stuZhengzhimianmao;

    @ApiModelProperty(value = "登录名", name = "loginName", example = "lx", required = true)
    private String loginName;

    @ApiModelProperty(value = "登录密码", name = "loginPw", example = "admin", required = true)
    private String loginPw;

    @ApiModelProperty(value = "删除标志", name = "del", example = "0", required = true)
    private String del;

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuXuehao() {
        return stuXuehao;
    }

    public void setStuXuehao(String stuXuehao) {
        this.stuXuehao = stuXuehao;
    }

    public String getStuRealname() {
        return stuRealname;
    }

    public void setStuRealname(String stuRealname) {
        this.stuRealname = stuRealname;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getStuAge() {
        return stuAge;
    }

    public void setStuAge(String stuAge) {
        this.stuAge = stuAge;
    }

    public String getStuCard() {
        return stuCard;
    }

    public void setStuCard(String stuCard) {
        this.stuCard = stuCard;
    }

    public String getStuZhengzhimianmao() {
        return stuZhengzhimianmao;
    }

    public void setStuZhengzhimianmao(String stuZhengzhimianmao) {
        this.stuZhengzhimianmao = stuZhengzhimianmao;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPw() {
        return loginPw;
    }

    public void setLoginPw(String loginPw) {
        this.loginPw = loginPw;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }
}