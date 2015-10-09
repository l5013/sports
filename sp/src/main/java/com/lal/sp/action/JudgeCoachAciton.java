package com.lal.sp.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lal.sp.bean.JCEvaluate;
import com.lal.sp.bean.JsonData;
import com.lal.sp.bean.JudgeCoach;
import com.lal.sp.bean.PageBean;
import com.lal.sp.service.JudgeCoachService;
import com.lal.sp.util.Conv;

@Controller
@RequestMapping("/JudgeCoach")
public class JudgeCoachAciton {
	
	@Autowired
	private JudgeCoachService judgeCoachService;
	
	@RequestMapping(value="register",consumes = "application/json",method=RequestMethod.POST)
	@ResponseBody
	public JsonData register(@RequestBody JudgeCoach jc) {
			JsonData data;
			try {
				judgeCoachService.register(jc);
				data = new JsonData("true");
			} catch (Exception e) { 
				e.printStackTrace();
				data = new JsonData("false");
			}
		return data;
	}
	
	@RequestMapping("/getJudgeCoach")
	@ResponseBody
	public JsonData getJudgeCoach(String sportsCategoryId,HttpServletRequest request) {
		JsonData data;
		try {
			//当前页
			String cp = request.getParameter("currentpage");
			int currentPage = 1;
			if(cp !=  null){
				currentPage = Integer.valueOf(cp);
			}
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("sportsCategoryId", sportsCategoryId);
			int totalCount = judgeCoachService.getJudgeCoachCount(params);
			List<JudgeCoach> list = judgeCoachService.getJudgeCoach(params,currentPage,Conv.pageSize);
			data = new JsonData("true", new PageBean(currentPage, Conv.pageSize, list, totalCount));
		} catch (Exception e) {
			e.printStackTrace();
			data = new JsonData("false");
		}
		return data;
	}
	
	@RequestMapping(value="/evaluate",consumes = "application/json",method=RequestMethod.POST)
	@ResponseBody
	public JsonData evaluate(@RequestBody JCEvaluate jcv) {
		JsonData data;
		try {
			judgeCoachService.evaluate(jcv);
			data = new JsonData("true");
		} catch (Exception e) {
			e.printStackTrace();
			data = new JsonData("false");
		}
		return data;
	}

}
