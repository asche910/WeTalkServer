package com.asche.wetalk.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class User implements Serializable {
    private Integer id;

    @NotBlank(message = "用户名不能为空！")
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotBlank(message = "密码不能为空！")
    @ApiModelProperty(value = "密码")
    private String password;

    private String nickname;

    private String gender;

    private String imgavatar;

    private String imgbg;

    private String signature;

    private String tel;

    private String email;

    private String address;

    private String profession;

    private String school;

    private String description;

    private Boolean isexpert;

    private Boolean isvip;

    private Integer follownum;

    private Integer followernum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImgavatar() {
        return imgavatar;
    }

    public void setImgavatar(String imgavatar) {
        this.imgavatar = imgavatar;
    }

    public String getImgbg() {
        return imgbg;
    }

    public void setImgbg(String imgbg) {
        this.imgbg = imgbg;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsexpert() {
        return isexpert;
    }

    public void setIsexpert(Boolean isexpert) {
        this.isexpert = isexpert;
    }

    public Boolean getIsvip() {
        return isvip;
    }

    public void setIsvip(Boolean isvip) {
        this.isvip = isvip;
    }

    public Integer getFollownum() {
        return follownum;
    }

    public void setFollownum(Integer follownum) {
        this.follownum = follownum;
    }

    public Integer getFollowernum() {
        return followernum;
    }

    public void setFollowernum(Integer followernum) {
        this.followernum = followernum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", nickname=").append(nickname);
        sb.append(", gender=").append(gender);
        sb.append(", imgavatar=").append(imgavatar);
        sb.append(", imgbg=").append(imgbg);
        sb.append(", signature=").append(signature);
        sb.append(", tel=").append(tel);
        sb.append(", email=").append(email);
        sb.append(", address=").append(address);
        sb.append(", profession=").append(profession);
        sb.append(", school=").append(school);
        sb.append(", description=").append(description);
        sb.append(", isexpert=").append(isexpert);
        sb.append(", isvip=").append(isvip);
        sb.append(", follownum=").append(follownum);
        sb.append(", followernum=").append(followernum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}