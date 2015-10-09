package com.lal.sp.dao;

import com.lal.sp.bean.SportsMatch;
import com.lal.sp.bean.UserSportsApply;

public interface ISportsMatchDao {

	void publishSports(SportsMatch sportsMatch);

	void applySports(UserSportsApply userSportsApply);

}
