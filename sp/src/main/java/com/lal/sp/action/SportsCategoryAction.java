package com.lal.sp.action;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lal.sp.bean.JsonData;
import com.lal.sp.bean.SportsCategory;
import com.lal.sp.service.SportsCategoryService;


@Controller
@RequestMapping("/SportsCategory")
public class SportsCategoryAction {
	
	@Autowired
	private SportsCategoryService sportsCategoryService;
	
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request,String searchInfo,HttpSession session) {
		List<SportsCategory> records = sportsCategoryService.getAllCategory();
		ModelAndView mv = new ModelAndView("../index");
		mv.addObject("records", records);
		mv.addObject("pagepath", "SportsCategory/list.jsp");
		return mv;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(SportsCategory sportsCategory) {
		sportsCategory.setStatus(1);
		sportsCategory.setCode(UUID.randomUUID().toString().replace("-", ""));
		sportsCategoryService.add(sportsCategory);
		return "redirect:/SportsCategory/list.do";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(HttpServletRequest request,@PathVariable String id) {
		sportsCategoryService.delete(id);
		return "redirect:/SportsCategory/list.do";
	}
	
	@RequestMapping("/getAllSportsCategory")
	@ResponseBody
	public JsonData getAllSportsCategory() {
		JsonData data;
		try {
			List<SportsCategory> list = sportsCategoryService.getAllCategory();
			data = new JsonData("true",list);
		} catch (Exception e) {
			e.printStackTrace();
			data = new JsonData("false");
		}
		return data;
	}
}
