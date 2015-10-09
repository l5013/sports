package com.lal.sp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lal.sp.bean.JsonData;
import com.lal.sp.bean.Place;
import com.lal.sp.bean.SportsMatch;
import com.lal.sp.bean.UserSportsApply;
import com.lal.sp.service.SportsMatchService;
import com.lal.sp.util.Conv;

@Controller
@RequestMapping("/SportsMatch")
public class SportsMatchAciton {
	
	@Autowired
	private SportsMatchService sportsMatchService;
	
	
	public JsonData getSportsList(String nearBy,HttpServletRequest request) {
		//当前页
		String cp = request.getParameter("currentpage");
		int currentPage = 1;
		if(cp !=  null){
			currentPage = Integer.valueOf(cp);
		}
		Map<String,Object> params = new HashMap<String, Object>();
		if(nearBy!=null && !"".equals(nearBy)) {
			params.put("nearBy", nearBy);
		}
		int totalCount = placeService.listPlacesCount(params);
		List<Place> recordList = placeService.listPlaces(params,currentPage,Conv.pageSize);
	}
	
	
	
	
	/**
	 * 用户发布活动
	 * @param sportsMatch
	 * @return
	 */
	@RequestMapping(value="/publishSports",consumes = "application/json",method=RequestMethod.POST)
	@ResponseBody
	public JsonData publishSports(@RequestBody SportsMatch sportsMatch) {
		JsonData data;
		try {
			sportsMatchService.publishSports(sportsMatch);
			data = new JsonData("true");
		} catch (Exception e) {
			e.printStackTrace();
			data = new JsonData("false");
		}
		return data;
	}
	
	/**
	 * 申请活动
	 * @param userSportsApply
	 * @return
	 */
	@RequestMapping(value="/publishSports",consumes = "application/json",method=RequestMethod.POST)
	@ResponseBody
	public JsonData applySports(@RequestBody UserSportsApply userSportsApply) {
		JsonData data;
		try {
			sportsMatchService.applySports(userSportsApply);
			data = new JsonData("true");
		} catch (Exception e) {
			e.printStackTrace();
			data = new JsonData("false");
		}
		return data;
	}
	
	

}
