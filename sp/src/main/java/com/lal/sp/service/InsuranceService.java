package com.lal.sp.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lal.sp.bean.Insurance;
import com.lal.sp.bean.Place;
import com.lal.sp.dao.IInsuranceDao;
import com.lal.sp.util.PageDaoUtil;

@Service
public class InsuranceService {
	
	@Autowired
	IInsuranceDao insuranceDao;
	@Autowired
	PageDaoUtil pageDaoUtil;
	public int listInsurancesCount(Map<String, Object> params) {
		return insuranceDao.listInsurancesCount(params);
	}
	public List<Insurance> listInsurances(Map<String, Object> params, int start, int limit) {
		return  pageDaoUtil.selectList("com.lal.sp.dao.IInsuranceDao.listInsurances", params, new RowBounds((start-1)*limit,limit));
	}
	public Insurance findInsuranceById(String id) {
		return insuranceDao.findInsuranceById(id);
	}
	public void delete(String id) {
		insuranceDao.delete(id);
	}
	public void update(Insurance insurance) {
		insuranceDao.update(insurance);
	}
	public void add(Insurance insurance) {
		insuranceDao.add(insurance);
	}

}
