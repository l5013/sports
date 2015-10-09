package com.lal.sp.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lal.sp.bean.Advertiser;
import com.lal.sp.bean.Place;
import com.lal.sp.dao.IAdvertiserDao;
import com.lal.sp.util.PageDaoUtil;

@Service
public class AdvertiserService {
	
	@Autowired
	private IAdvertiserDao advertiserDao;
	@Autowired
	private PageDaoUtil pageDaoUtil;

	public int listAdvertisersCount(Map<String, Object> params) {
		return advertiserDao.listAdvertisersCount(params);
	}

	public List<Place> listAdvertisers(Map<String, Object> params, int start, int limit) {
		return  pageDaoUtil.selectList("com.lal.sp.dao.IAdvertiserDao.listAdvertisers", params, new RowBounds((start-1)*limit,limit));
	}

	public void add(Advertiser advertiser) {
		advertiserDao.add(advertiser);
	}

	public Advertiser findAdvertiserById(String id) {
		return advertiserDao.findAdvertiserById(id);
	}

	public void update(Advertiser advertiser) {
		advertiserDao.update(advertiser);
	}

	public void delete(String id) {
		advertiserDao.delete(id);
	}

}
