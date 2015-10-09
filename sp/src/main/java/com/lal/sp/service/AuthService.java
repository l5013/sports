package com.lal.sp.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lal.sp.bean.Admin;
import com.lal.sp.dao.IAuthDao;

@Service
public class AuthService {

	@Autowired
	private IAuthDao authDao;
	
	public Admin login(Map<String, Object> params) {
		return authDao.login(params);
	}

}
