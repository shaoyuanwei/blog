package com.syw.blog.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.syw.blog.ptool.GeneralViews;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Power implements Serializable {

    public interface PowerSimpleView extends GeneralViews.INormalView {};
    public interface PowerLodingView extends PowerSimpleView{};

    @JsonView(PowerLodingView.class)
    private Integer id;

    @JsonView(PowerLodingView.class)
    private Integer powerCode;

    @JsonView(PowerLodingView.class)
    private String powerName;

    private Date createTime;

    private Date updateTime;

    private Integer createAccount;

    private Integer updateAccount;

    private Integer isDel;

    @JsonView(PowerSimpleView.class)
    private String url;

    @JsonView(PowerSimpleView.class)
    private Integer fatherCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPowerCode() {
        return powerCode;
    }

    public void setPowerCode(Integer powerCode) {
        this.powerCode = powerCode;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getFatherCode() {
        return fatherCode;
    }

    public void setFatherCode(Integer fatherCode) {
        this.fatherCode = fatherCode;
    }

    @Override
    public String toString() {
        return "Power{" +
                "id=" + id +
                ", powerCode=" + powerCode +
                ", powerName='" + powerName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createAccount=" + createAccount +
                ", updateAccount=" + updateAccount +
                ", isDel=" + isDel +
                ", url='" + url + '\'' +
                ", fatherCode=" + fatherCode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Power power = (Power) o;
        return Objects.equals(id, power.id) &&
                Objects.equals(powerCode, power.powerCode) &&
                Objects.equals(powerName, power.powerName) &&
                Objects.equals(createTime, power.createTime) &&
                Objects.equals(updateTime, power.updateTime) &&
                Objects.equals(createAccount, power.createAccount) &&
                Objects.equals(updateAccount, power.updateAccount) &&
                Objects.equals(isDel, power.isDel) &&
                Objects.equals(url, power.url) &&
                Objects.equals(fatherCode, power.fatherCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, powerCode, powerName, createTime, updateTime, createAccount, updateAccount, isDel, url, fatherCode);
    }
}