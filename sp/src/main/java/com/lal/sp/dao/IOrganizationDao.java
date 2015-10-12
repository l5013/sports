package com.lal.sp.dao;

import java.util.Map;

import com.lal.sp.bean.Organization;
import com.lal.sp.bean.User;

public interface IOrganizationDao {

	void add(Organization organization);

	void addOrganizationSportsCategory(Organization organization);

	Organization getOrganizationById(String id);

	void updatePwd(Map<String, Object> param);

	User login(Map<String, Object> param);

	void addJifen(Organization organization);

}
