package com.lal.sp.bean;

import java.util.List;

public class SportsMatch {
	
	private Integer id;
	private String name;
	private String picture;
	private String content;
	private String startTime;
	private String endTime;
	private String joinEndTime;
	private Integer persons;
	private String cost;
	private String address;
	private User publisher;
	private String province;
	private String city;
	private String district;
	private Double longitude;
	private Double latitude;
	private Integer status; //活动状态 1申请中2申请成功3申请失败
	private String remark;
	//比赛字段
	private Place site;
	private Insurance insurance;
	private Integer type; //类型 1比赛2活动
	private List<JudgeCoach> judgeCoachs;
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
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getJoinEndTime() {
		return joinEndTime;
	}
	public void setJoinEndTime(String joinEndTime) {
		this.joinEndTime = joinEndTime;
	}
	public Integer getPersons() {
		return persons;
	}
	public void setPersons(Integer persons) {
		this.persons = persons;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User getPublisher() {
		return publisher;
	}
	public void setPublisher(User publisher) {
		this.publisher = publisher;
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
	
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
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
	public Place getSite() {
		return site;
	}
	public void setSite(Place site) {
		this.site = site;
	}
	public Insurance getInsurance() {
		return insurance;
	}
	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public List<JudgeCoach> getJudgeCoachs() {
		return judgeCoachs;
	}
	public void setJudgeCoachs(List<JudgeCoach> judgeCoachs) {
		this.judgeCoachs = judgeCoachs;
	}
	
}
