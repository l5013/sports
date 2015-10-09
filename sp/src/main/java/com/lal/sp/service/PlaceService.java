package com.lal.sp.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lal.sp.bean.Place;
import com.lal.sp.dao.IPlaceDao;
import com.lal.sp.util.PageDaoUtil;

@Service
public class PlaceService {
	
	@Autowired
	private IPlaceDao placeDao;
	@Autowired
	private PageDaoUtil pageDaoUtil;

	public int listPlacesCount(Map<String, Object> params) {
		return placeDao.listPlacesCount(params);
	}

	public List<Place> listPlaces(Map<String, Object> params, int start, int limit) {
		return  pageDaoUtil.selectList("com.lal.sp.dao.IPlaceDao.listPlaces", params, new RowBounds((start-1)*limit,limit));
	}

	public void add(Place place) {
		placeDao.add(place);
	}

	public Place findPlaceById(String id) {
		return placeDao.findPlaceById(id);
	}

	public void update(Place place) {
		placeDao.update(place);
	}

	public void delete(String id) {
		placeDao.delete(id);
	}

}
