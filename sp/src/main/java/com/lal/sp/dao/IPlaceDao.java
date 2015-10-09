package com.lal.sp.dao;

import java.util.List;
import java.util.Map;

import com.lal.sp.bean.Place;

public interface IPlaceDao {

	int listPlacesCount(Map<String, Object> params);

	List<Place> listPlaces(Map<String, Object> params);

	void add(Place place);

	Place findPlaceById(String id);

	void update(Place place);

	void delete(String id);
}
