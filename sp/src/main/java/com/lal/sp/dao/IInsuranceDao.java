package com.lal.sp.dao;

import java.util.List;
import java.util.Map;

import com.lal.sp.bean.Insurance;

public interface IInsuranceDao {

	int listInsurancesCount(Map<String, Object> params);
	
	List<Insurance> listInsurances(Map<String, Object> params);
	
	Insurance findInsuranceById(String id);

	void delete(String id);

	void update(Insurance insurance);

	void add(Insurance insurance);

}
