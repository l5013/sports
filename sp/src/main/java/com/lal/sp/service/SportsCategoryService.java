package com.lal.sp.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lal.sp.bean.SportsCategory;
import com.lal.sp.dao.ISportsCategoryDao;
import com.lal.sp.util.PageDaoUtil;

@Service
public class SportsCategoryService {
	
	@Autowired
	private ISportsCategoryDao sportsCategoryDao;
	@Autowired
	private PageDaoUtil pageDaoUtil;

	public int listSportsCategoryCount(Map<String, Object> params) {
		return sportsCategoryDao.listSportsCategoryCount(params);
	}

	public List<SportsCategory> listSportsCategory(Map<String, Object> params, int start, int limit) {
		return  pageDaoUtil.selectList("com.lal.sp.dao.ISportsCategoryDao.listSportsCategory", params, new RowBounds((start-1)*limit,limit));
	}

	public List<SportsCategory> getAllCategory() {
		return sportsCategoryDao.getAllCategory();
	}

	public void add(SportsCategory sportsCategory) {
		sportsCategoryDao.add(sportsCategory);
	}

	public void delete(String id) {
		sportsCategoryDao.delete(id);
	}

}
