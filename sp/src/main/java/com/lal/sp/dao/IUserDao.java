package com.lal.sp.dao;

import java.util.HashMap;
import java.util.Map;

import com.lal.sp.bean.SportsMatch;
import com.lal.sp.bean.User;
import com.lal.sp.bean.UserSportsApply;

public interface IUserDao {

	void add(User u);

	void updatePwd(Map<String, Object> param);

	User login(Map<String, Object> param);

	void addUserSportsCategory(User u);

	User getUserById(String id);

	void updateJudgeCoach(HashMap<String, Object> param);

	void publishSports(SportsMatch sportsMatch);

	void applySports(UserSportsApply userSportsApply);

}
