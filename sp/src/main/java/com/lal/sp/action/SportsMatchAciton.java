package com.lal.sp.action;

import java.util.ArrayList;
import java.util.Collections;
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
import com.lal.sp.bean.PageBean;
import com.lal.sp.bean.SportsMatch;
import com.lal.sp.bean.SportsMatchEvaluate;
import com.lal.sp.bean.UserSportsApply;
import com.lal.sp.bean.UserSportsMatchApply;
import com.lal.sp.service.SportsMatchService;
import com.lal.sp.util.Conv;
import com.lal.sp.util.DistanceComparator;

@Controller
@RequestMapping("/SportsMatch")
public class SportsMatchAciton {
	
	@Autowired
	private SportsMatchService sportsMatchService;
	
	/**
	 * 根据状态获取所有活动列表
	 * @param status
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAllSportsList")
	@ResponseBody
	public JsonData getAllSportsList(String status,HttpServletRequest request) {
		JsonData data;
		try {
			//当前页
			String cp = request.getParameter("currentpage");
			int currentPage = 1;
			if(cp !=  null){
				currentPage = Integer.valueOf(cp);
			}
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("status", status);
			int totalCount = sportsMatchService.listAllSportsCount(params);
			List<SportsMatch> list = sportsMatchService.listAllSports(params,currentPage,Conv.pageSize);
			data = new JsonData("true", new PageBean(currentPage, Conv.pageSize, list, totalCount));
		} catch (Exception e) {
			e.printStackTrace();
			data = new JsonData("false");
		}
		return data;
	}
	
	@RequestMapping("/getNearBySports")
	@ResponseBody
	public JsonData getNearBySports(Double distance,Double longitude,Double latitude,HttpServletRequest request) {
		JsonData data;
		try {
			//当前页
			String cp = request.getParameter("currentpage");
			int currentPage = 1;
			if(cp !=  null){
				currentPage = Integer.valueOf(cp);
			}
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("distance", distance);
			params.put("latitude", latitude);
			params.put("longitude", longitude);
			int totalCount = sportsMatchService.listNearBySportsCount(params);
			List<SportsMatch> list = sportsMatchService.listNearBySports(params);
			Collections.sort(list, new DistanceComparator(longitude, latitude));
			//矩阵算法不能实现排序、分页  这里使用list的假分页
			List<SportsMatch> newList = getPagerList(currentPage, Conv.pageSize, totalCount, list);
			data = new JsonData("true", new PageBean(currentPage, Conv.pageSize, newList, totalCount));
		} catch (Exception e) {
			e.printStackTrace();
			data = new JsonData("false");
		}
		return data;
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
	@RequestMapping(value="/applySports",consumes = "application/json",method=RequestMethod.POST)
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
	
	/**
	 * 获取活动申请列表
	 * @param sportsId
	 * @return
	 */
	@RequestMapping("/getApplySportsList")
	@ResponseBody
	public JsonData getApplySportsList(String sportsId) {
		JsonData data;
		try {
			HashMap<String, Object> userParams = new HashMap<String, Object>();
			userParams.put("sportsId", sportsId);
			userParams.put("user", true);
			List<UserSportsApply> userApplyList = sportsMatchService.getApplySportsList(userParams);
			HashMap<String, Object> peopleParams = new HashMap<String, Object>();
			peopleParams.put("sportsId", sportsId);
			peopleParams.put("people", true);
			List<UserSportsApply> peopleApplyList = sportsMatchService.getApplySportsList(peopleParams);
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("userApplyList", userApplyList);
			resultMap.put("peopleApplyList", peopleApplyList);
			data = new JsonData("true",resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			data = new JsonData("false");
		}
		return data;
	}
	
	/**
	 * 审批活动申请
	 * @param sportsId
	 * @param user
	 * @return
	 */
	@RequestMapping("/auditingApplySports")
	@ResponseBody
	public JsonData auditingApplySports(String sportsId,String user) {
		JsonData data;
		try {
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("sportsId", sportsId);
			params.put("user", user);
			sportsMatchService.auditingApplySports(params);
			data = new JsonData("true");
		} catch (Exception e) {
			e.printStackTrace();
			data = new JsonData("false");
		}
		return data;
	}
	
	
	/**
	 * 查看个人所发布的活动
	 * @param userId
	 * @return
	 */
	@RequestMapping("/getMyPushlishSports")
	@ResponseBody
	public JsonData getAllPushlishSports(String userId) {
		JsonData data;
		try {
			List<SportsMatch> list = sportsMatchService.getMyPushlishSports(userId);
			data = new JsonData("true",list);
		} catch (Exception e) {
			e.printStackTrace();
			data= new JsonData("false");
		}
		return data;
	}
	
	
	/**
	 * 查看个人所申请的活动
	 * @param user
	 * @return
	 */
	@RequestMapping("/getMyApplySports")
	@ResponseBody
	public JsonData getMyApplySports(String user) {
		JsonData data;
		try {
			List<UserSportsApply> list = sportsMatchService.getMyApplySports(user);
			data = new JsonData("true",list);
		} catch (Exception e) {
			e.printStackTrace();
			data = new JsonData("false");
		}
		return data;
	}
	
	/**
	 * 评价活动
	 * @param sportsMatchEvaluate
	 * @return
	 */
	@RequestMapping(value="/evaluateSports",consumes = "application/json",method=RequestMethod.POST)
	@ResponseBody
	public JsonData evaluateSports(@RequestBody SportsMatchEvaluate sportsMatchEvaluate) {
		JsonData data;
		try {
			sportsMatchService.evaluateSports(sportsMatchEvaluate);
			data = new JsonData("true");
		} catch (Exception e) {
			e.printStackTrace();
			data = new JsonData("false");
		}
		return data;
	}
	
	
	/**************************************************************************************************/
	
	/**
	 * 根据状态获取所有组织发布的活动和比赛列表
	 * @param status
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAllSportsMatchList")
	@ResponseBody
	public JsonData getAllSportsMatchList(String status,String type,HttpServletRequest request) {
		JsonData data;
		try {
			//当前页
			String cp = request.getParameter("currentpage");
			int currentPage = 1;
			if(cp !=  null){
				currentPage = Integer.valueOf(cp);
			}
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("status", status);
			params.put("type", type);
			int totalCount = sportsMatchService.listAllSportsMatchCount(params);
			List<SportsMatch> list = sportsMatchService.listAllSportsMatch(params,currentPage,Conv.pageSize);
			data = new JsonData("true", new PageBean(currentPage, Conv.pageSize, list, totalCount));
		} catch (Exception e) {
			e.printStackTrace();
			data = new JsonData("false");
		}
		return data;
	}
	
	@RequestMapping("/getNearBySportsMatch")
	@ResponseBody
	public JsonData getNearBySportsMatch(String type,Double distance,Double longitude,Double latitude,HttpServletRequest request) {
		JsonData data;
		try {
			//当前页
			String cp = request.getParameter("currentpage");
			int currentPage = 1;
			if(cp !=  null){
				currentPage = Integer.valueOf(cp);
			}
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("distance", distance);
			params.put("latitude", latitude);
			params.put("longitude", longitude);
			params.put("type", type);
			int totalCount = sportsMatchService.listNearBySportsMatchCount(params);
			List<SportsMatch> list = sportsMatchService.listNearBySportsMatch(params);
			Collections.sort(list, new DistanceComparator(longitude, latitude));
			List<SportsMatch> newList = getPagerList(currentPage, Conv.pageSize, totalCount, list);
			data = new JsonData("true", new PageBean(currentPage, Conv.pageSize, newList, totalCount));
		} catch (Exception e) {
			e.printStackTrace();
			data = new JsonData("false");
		}
		return data;
	}
	
	
	/**
	 * 组织发布活动或比赛
	 * @param sportsMatch
	 * @return
	 */
	@RequestMapping(value="/publishSportsMatch",consumes = "application/json",method=RequestMethod.POST)
	@ResponseBody
	public JsonData publishSportsMatch(@RequestBody SportsMatch sportsMatch) {
		JsonData data;
		try {
			sportsMatchService.publishSportsMatch(sportsMatch);
			data = new JsonData("true");
		} catch (Exception e) {
			e.printStackTrace();
			data = new JsonData("false");
		}
		return data;
	}
	
	/**
	 * 申请组织发布的活动或比赛
	 * @param userSportsApply
	 * @return
	 */
	@RequestMapping(value="/applySportsMatch",consumes = "application/json",method=RequestMethod.POST)
	@ResponseBody
	public JsonData applySportsMatch(@RequestBody UserSportsMatchApply userSportsMatchApply) {
		JsonData data;
		try {
			sportsMatchService.applySportsMatch(userSportsMatchApply);
			data = new JsonData("true");
		} catch (Exception e) {
			e.printStackTrace();
			data = new JsonData("false");
		}
		return data;
	}
	
	/**
	 * 获取活动比赛申请列表
	 * @param sportsId
	 * @return
	 */
	@RequestMapping("/getApplySportsMatchList")
	@ResponseBody
	public JsonData getApplySportsMatchList(String sportsMatchId) {
		JsonData data;
		try {
			List<UserSportsApply> userApplyList = sportsMatchService.getApplySportsMatchList(sportsMatchId);
			data = new JsonData("true",userApplyList);
		} catch (Exception e) {
			e.printStackTrace();
			data = new JsonData("false");
		}
		return data;
	}
	
	/**
	 * 审批活动比赛申请
	 * @param sportsId
	 * @param user
	 * @return
	 */
	@RequestMapping("/auditingApplySportsMatch")
	@ResponseBody
	public JsonData auditingApplySportsMatch(String sportsMatchId,String userId) {
		JsonData data;
		try {
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("sportsMatchId", sportsMatchId);
			params.put("userId", userId);
			sportsMatchService.auditingApplySportsMatch(params);
			data = new JsonData("true");
		} catch (Exception e) {
			e.printStackTrace();
			data = new JsonData("false");
		}
		return data;
	}
	
	/**
	 * 查看组织发布的活动或比赛
	 * @param userId
	 * @return
	 */
	@RequestMapping("/getAllPushlishSportsMatch")
	@ResponseBody
	public JsonData getAllPushlishSportsMatch(String oId) {
		JsonData data;
		try {
			List<SportsMatch> list = sportsMatchService.getAllPushlishSportsMatch(oId);
			data = new JsonData("true",list);
		} catch (Exception e) {
			e.printStackTrace();
			data= new JsonData("false");
		}
		return data;
	}
	
	
	/**
	 * 查看个人所申请的活动
	 * @param user
	 * @return
	 */
	@RequestMapping("/getMyApplySportsMatch")
	@ResponseBody
	public JsonData getMyApplySportsMatch(String userId) {
		JsonData data;
		try {
			List<UserSportsMatchApply> list = sportsMatchService.getMyApplySportsMatch(userId);
			data = new JsonData("true",list);
		} catch (Exception e) {
			e.printStackTrace();
			data = new JsonData("false");
		}
		return data;
	}
	
	/**
	 * 评价比赛或活动
	 * @param sportsMatchEvaluate
	 * @return
	 */
	@RequestMapping(value="/evaluateSportsMatch",consumes = "application/json",method=RequestMethod.POST)
	@ResponseBody
	public JsonData evaluateSportsMatch(@RequestBody SportsMatchEvaluate sportsMatchEvaluate) {
		JsonData data;
		try {
			sportsMatchService.evaluateSportsMatch(sportsMatchEvaluate);
			data = new JsonData("true");
		} catch (Exception e) {
			e.printStackTrace();
			data = new JsonData("false");
		}
		return data;
	}
	
	
	
	/**
	 * List 假分页
	 * @param currentPage
	 * @param avgRows
	 * @param totalRows
	 * @param list
	 * @return
	 */
	private <T> List<T> getPagerList(Integer currentPage,Integer avgRows,Integer totalRows,List<T> list) {  
        List<T> newList = new ArrayList<T>();  
        for(int i = (currentPage - 1) * avgRows; i < totalRows && i < currentPage * avgRows; i++) {  
            newList.add(list.get(i));  
        }  
        return newList;  
    }

}
