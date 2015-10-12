package com.lal.sp.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lal.sp.bean.JsonData;
import com.lal.sp.bean.User;
import com.lal.sp.service.UserService;
import com.lal.sp.util.MyMD5Util;

@Controller
@RequestMapping("/User")
public class UserAciton {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/register",consumes = "application/json",method=RequestMethod.POST)
	@ResponseBody
	public JsonData register(@RequestBody User u) {
		u.setPassword(MyMD5Util.getMD5(u.getPassword().getBytes()));
			JsonData data;
			try {
				userService.register(u);
				data = new JsonData("true");
			} catch (Exception e) { 
				e.printStackTrace();
				data = new JsonData("false");
			}
		return data;
	}
	
	@RequestMapping(value="/updatePwd")
	@ResponseBody
	public JsonData updatePwd(String id, String newPwd) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("password", MyMD5Util.getMD5(newPwd.getBytes()));
		JsonData data;
		try {
			userService.updatePwd(param);
			data = new JsonData("true");
		} catch (Exception e) {
			e.printStackTrace();
			data = new JsonData("false");
		}
		return data;
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public JsonData login(String username, String password) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("username", username);
		param.put("password", password);
		JsonData data;
		try {
			User user = userService.login(param);
			data = new JsonData("true", user);
		} catch (Exception e) {
			e.printStackTrace();
			data = new JsonData("false");
		}
		return data;
	}
	

}
