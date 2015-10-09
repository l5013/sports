package com.lal.sp.bean;

import java.util.List;

public class User {
	
	private Integer id;
	private String name;
	private String nickname;
	private String picture;
	private String phone;
	private String password;
	private Integer age;
	private String gender;
	private String email;
	private String qq;
	private String weixin;
	private String interest;
	private String introduction;
	private String province;
	private String city;
	private String district;
	private Integer job;  //职业 1学生2已就业3未就业4退休5其他
	private Integer jifen;
	private Integer status;
	private String remark;
	private JudgeCoach judgeCoach;
	private List<SportsCategory> sportsCategory;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public Integer getJob() {
		return job;
	}
	public void setJob(Integer job) {
		this.job = job;
	}
	public Integer getJifen() {
		return jifen;
	}
	public void setJifen(Integer jifen) {
		this.jifen = jifen;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public JudgeCoach getJudgeCoach() {
		return judgeCoach;
	}
	public void setJudgeCoach(JudgeCoach judgeCoach) {
		this.judgeCoach = judgeCoach;
	}
	public List<SportsCategory> getSportsCategory() {
		return sportsCategory;
	}
	public void setSportsCategory(List<SportsCategory> sportsCategory) {
		this.sportsCategory = sportsCategory;
	}
	
}
