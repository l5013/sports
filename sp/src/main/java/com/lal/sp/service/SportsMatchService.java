package com.lal.sp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lal.sp.bean.SportsMatch;
import com.lal.sp.bean.UserSportsApply;
import com.lal.sp.dao.ISportsMatchDao;

@Service
public class SportsMatchService {

	@Autowired
	private ISportsMatchDao sportsMatchDao;

	public void publishSports(SportsMatch sportsMatch) {
		sportsMatchDao.publishSports(sportsMatch);
	}

	public void applySports(UserSportsApply userSportsApply) {
		sportsMatchDao.applySports(userSportsApply);
	}
}
