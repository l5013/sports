package com.lal.sp.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lal.sp.bean.Organization;
import com.lal.sp.bean.User;
import com.lal.sp.dao.IOrganizationDao;

@Service
public class OrganizationService {
	
	@Autowired
	private IOrganizationDao organizationDao;

	@Transactional
	public void register(Organization organization) {
		organizationDao.add(organization);
		if(organization.getSportsCategory()!=null && organization.getSportsCategory().size()>0) {
			organizationDao.addOrganizationSportsCategory(organization);
		}
	}

	public Organization getOrganizationById(String id) {
		return organizationDao.getOrganizationById(id);
	}
	
	public void updatePwd(Map<String, Object> param) {
		
		organizationDao.updatePwd(param);
		
	}
	
	public User login(Map<String, Object> param) {
		return organizationDao.login(param);
	}

	public void addJifen(Organization organization) {
		organizationDao.addJifen(organization);
	}

}
