package com.lal.sp.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lal.sp.bean.SportsMatch;
import com.lal.sp.bean.User;
import com.lal.sp.bean.UserSportsApply;
import com.lal.sp.dao.IUserDao;

@Service
public class UserService {
	
	@Autowired
	IUserDao userDao;
	
	@Transactional
	public void register(User u) {
		
		userDao.add(u);
		if(u.getSportsCategory()!=null && u.getSportsCategory().size()>0) {
			userDao.addUserSportsCategory(u);
		}
		
	}

	public void updatePwd(Map<String, Object> param) {
		
		userDao.updatePwd(param);
		
	}

	public User login(Map<String, Object> param) {
		return userDao.login(param);
	}

	public User getUserById(String id) {
		return userDao.getUserById(id);
	}

	public void addJifen(User user) {
		userDao.addJifen(user);
	}

}
