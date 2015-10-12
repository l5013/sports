package com.lal.sp.bean;

public class UserSportsMatchApply {
	private Integer id;
	private SportsMatch sportsMatch;
	private User user;
	private Integer status; //0未通过，1，通过
	private String remark;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public SportsMatch getSportsMatch() {
		return sportsMatch;
	}
	public void setSportsMatch(SportsMatch sportsMatch) {
		this.sportsMatch = sportsMatch;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	
	
}
