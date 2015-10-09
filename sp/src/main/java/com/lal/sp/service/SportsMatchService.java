package com.lal.sp.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lal.sp.bean.Place;
import com.lal.sp.bean.SportsMatch;
import com.lal.sp.bean.UserSportsApply;
import com.lal.sp.dao.ISportsMatchDao;
import com.lal.sp.util.PageDaoUtil;

@Service
public class SportsMatchService {

	@Autowired
	private ISportsMatchDao sportsMatchDao;
	@Autowired
	private PageDaoUtil pageDaoUtil;

	public void publishSports(SportsMatch sportsMatch) {
		sportsMatchDao.publishSports(sportsMatch);
	}

	public void applySports(UserSportsApply userSportsApply) {
		sportsMatchDao.applySports(userSportsApply);
	}

	public int listAllSportsCount(Map<String, Object> params) {
		return sportsMatchDao.listAllSportsCount(params);
	}

	public List<Place> listAllSports(Map<String, Object> params, int start, int limit) {
		return pageDaoUtil.selectList("com.lal.sp.dao.ISportsMatchDao.listAllSports", params, new RowBounds((start-1)*limit,limit));
	}
}
