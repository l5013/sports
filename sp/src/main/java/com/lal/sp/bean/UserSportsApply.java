package com.lal.sp.bean;

/**
 * 
 * @author anliang
 *	用户活动申请
 */
public class UserSportsApply {
	
	private SportsMatch sportsMatch;
	private User user;
	private String phone;
	private String name;
	private Integer status; //0未通过，1，通过
	private String remark;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
