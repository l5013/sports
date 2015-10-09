package com.lal.sp.action;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lal.sp.bean.Place;
import com.lal.sp.bean.SportsCategory;
import com.lal.sp.service.SportsCategoryService;
import com.lal.sp.util.FilesUtil;


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
}
