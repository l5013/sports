package com.lal.sp.dao;

import java.util.List;
import java.util.Map;

import com.lal.sp.bean.Advertiser;

public interface IAdvertiserDao {

	int listAdvertisersCount(Map<String, Object> params);
	
	List<Advertiser> listAdvertisers(Map<String, Object> params);

	void add(Advertiser advertiser);

	Advertiser findAdvertiserById(String id);

	void update(Advertiser advertiser);

	void delete(String id);

}
