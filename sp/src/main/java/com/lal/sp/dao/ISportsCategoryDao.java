package com.lal.sp.dao;

import java.util.List;
import java.util.Map;

import com.lal.sp.bean.SportsCategory;

public interface ISportsCategoryDao {

	int listSportsCategoryCount(Map<String, Object> params);
	
	List<SportsCategory> listSportsCategory(Map<String, Object> params);

	List<SportsCategory> getAllCategory();

	void add(SportsCategory sportsCategory);

	void delete(String id);

}
