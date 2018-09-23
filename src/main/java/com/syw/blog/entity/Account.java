package com.syw.blog.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.syw.blog.ptool.GeneralViews;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {

    public interface AccountSimpleView extends GeneralViews.INormalView {};
    public interface AccountDetailView extends AccountSimpleView{};

    private Integer id;

    @JsonView({AccountDetailView.class, AccountSimpleView.class})
    private String account;

    private String password;

    @JsonView(AccountDetailView.class)
    private String power;

    private Date createTime;

    @JsonView({AccountDetailView.class, AccountSimpleView.class})
    private Date updateTime;

    private Integer createAccount;

    private Integer updateAccount;

    private Integer isDel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power == null ? null : power.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(Integer createAccount) {
        this.createAccount = createAccount;
    }

    public Integer getUpdateAccount() {
        return updateAccount;
    }

    public void setUpdateAccount(Integer updateAccount) {
        this.updateAccount = updateAccount;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", power='" + power + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createAccount=" + createAccount +
                ", updateAccount=" + updateAccount +
                ", isDel=" + isDel +
                '}';
    }
}