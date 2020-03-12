/**
 * FileName: BusinessUser
 * Author: WP
 * Date: 2020/3/8 18:14
 * Description:
 * History:
 **/
package com.badminton.mall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BusinessUser {
    private Integer businessUserId;

    private String loginName;

    private String password;

    private String nickName;

    private Byte locked;

    private Byte isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getBusinessUserId() {
        return businessUserId;
    }

    public void setBusinessUserId(Integer businessUserId) {
        this.businessUserId = businessUserId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public Byte getLocked() {
        return locked;
    }

    public void setLocked(Byte locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", businessUserId=").append(businessUserId);
        sb.append(", loginName=").append(loginName);
        sb.append(", password=").append(password);
        sb.append(", nickName=").append(nickName);
        sb.append(", locked=").append(locked);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", create_time=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}
