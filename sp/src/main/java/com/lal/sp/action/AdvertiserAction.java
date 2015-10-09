package com.lal.sp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.lal.sp.bean.Advertiser;
import com.lal.sp.bean.PageBean;
import com.lal.sp.bean.Place;
import com.lal.sp.bean.SportsCategory;
import com.lal.sp.service.AdvertiserService;
import com.lal.sp.service.PlaceService;
import com.lal.sp.service.SportsCategoryService;
import com.lal.sp.util.Conv;
import com.lal.sp.util.FilesUtil;


@Controller
@RequestMapping("/Advertiser")
public class AdvertiserAction {
	
	@Autowired
	private AdvertiserService advertiserService;
	
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request,String searchInfo,HttpSession session) {
		//当前页
		String cp = request.getParameter("currentpage");
		int currentPage = 1;
		if(cp !=  null){
			currentPage = Integer.valueOf(cp);
		}
		Map<String,Object> params = new HashMap<String, Object>();
		if(searchInfo!=null && !"".equals(searchInfo)) {
			params.put("searchInfo", searchInfo);
		}
		int totalCount = advertiserService.listAdvertisersCount(params);
		List<Place> recordList = advertiserService.listAdvertisers(params,currentPage,Conv.pageSize);
		ModelAndView mv = new ModelAndView("../index");
		mv.addObject("currentPage", currentPage);
		mv.addObject("pageBean", new PageBean(currentPage, Conv.pageSize, recordList, totalCount));
		mv.addObject("pagepath", "Advertiser/list.jsp");
		return mv;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("../index");
		mv.addObject("pagepath", "Advertiser/add.jsp");
		return mv;
	}

	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Advertiser advertiser, HttpServletRequest request,HttpSession session,@PathParam(value="file") MultipartFile file) {
		advertiser.setStatus(1);
		if(!file.isEmpty()) {
			String realPath = request.getSession().getServletContext().getRealPath("/");
			String filename = FilesUtil.saveFile(file,realPath,"/upload/advertiser/");
			advertiser.setPic(filename);
		}
		advertiserService.add(advertiser);
		return "redirect:/Advertiser/list.do";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public ModelAndView update(HttpServletRequest request,@PathVariable String id) {
		ModelAndView mv = new ModelAndView("../index");
		mv.addObject("pagepath", "Advertiser/update.jsp");
		Advertiser advertiser = advertiserService.findAdvertiserById(id);
		mv.addObject("advertiser",advertiser);
		return mv;
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(Advertiser advertiser, HttpServletRequest request,HttpSession session,@PathParam(value="file") MultipartFile file) {
		if(!file.isEmpty()) {
			String realPath = request.getSession().getServletContext().getRealPath("/");
			String filename = FilesUtil.saveFile(file,realPath,"/upload/advertiser/");
			advertiser.setPic(filename);
		}
		advertiserService.update(advertiser);
		return "redirect:/Advertiser/list.do";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(HttpServletRequest request,@PathVariable String id) {
		advertiserService.delete(id);
		return "redirect:/Advertiser/list.do";
	}
	
}
