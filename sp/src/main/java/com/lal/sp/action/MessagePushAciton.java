package com.lal.sp.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lal.sp.bean.JsonData;
import com.lal.sp.util.PushUtil;


@Controller
@RequestMapping("/MessagePush")
public class MessagePushAciton {
	
	
	@RequestMapping(value="/push",method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView push() {
		ModelAndView mv = new ModelAndView("../index");
		mv.addObject("pagepath", "MessagePush/push.jsp");
		return mv;
	}
	
	
	@RequestMapping(value="/push",method=RequestMethod.POST)
	@ResponseBody
	public JsonData push(String content) {
		JsonData data;
		try {
			PushUtil.push(null, content);
			data = new JsonData("true");
		} catch (Exception e) {
			e.printStackTrace();
			data = new JsonData("false");
		}
		return data;
	}
	
}
