package com.lal.sp.dao;

import java.util.Map;

import com.lal.sp.bean.Admin;

public interface IAuthDao {

	Admin login(Map<String, Object> params);

}
