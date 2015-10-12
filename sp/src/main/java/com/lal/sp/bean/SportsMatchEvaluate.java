package com.lal.sp.bean;

public class SportsMatchEvaluate {
	
	private SportsMatch sportsMatch;
	
	private User user;
	
	private Integer evaluate;
	
	private String evaluateContent;
	
	private Integer jifen;

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

	public Integer getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Integer evaluate) {
		this.evaluate = evaluate;
	}

	public String getEvaluateContent() {
		return evaluateContent;
	}

	public void setEvaluateContent(String evaluateContent) {
		this.evaluateContent = evaluateContent;
	}

	public Integer getJifen() {
		return jifen;
	}

	public void setJifen(Integer jifen) {
		this.jifen = jifen;
	}
	
}
