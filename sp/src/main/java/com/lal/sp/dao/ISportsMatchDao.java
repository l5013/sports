package com.lal.sp.dao;

import java.util.List;
import java.util.Map;

import com.lal.sp.bean.SportsMatch;
import com.lal.sp.bean.UserSportsApply;

public interface ISportsMatchDao {

	void publishSports(SportsMatch sportsMatch);

	void applySports(UserSportsApply userSportsApply);

	int listAllSportsCount(Map<String, Object> params);

	List<SportsMatch> listAllSports(Map<String, Object> params);
}
