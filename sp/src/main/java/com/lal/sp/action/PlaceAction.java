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

import com.lal.sp.bean.PageBean;
import com.lal.sp.bean.Place;
import com.lal.sp.bean.SportsCategory;
import com.lal.sp.service.PlaceService;
import com.lal.sp.service.SportsCategoryService;
import com.lal.sp.util.Conv;
import com.lal.sp.util.FilesUtil;


@Controller
@RequestMapping("/Place")
public class PlaceAction {
	
	@Autowired
	private PlaceService placeService;
	@Autowired
	private SportsCategoryService sportsCategoryService;
	
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
		int totalCount = placeService.listPlacesCount(params);
		List<Place> recordList = placeService.listPlaces(params,currentPage,Conv.pageSize);
		ModelAndView mv = new ModelAndView("../index");
		mv.addObject("currentPage", currentPage);
		mv.addObject("pageBean", new PageBean(currentPage, Conv.pageSize, recordList, totalCount));
		mv.addObject("pagepath", "Place/list.jsp");
		return mv;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("../index");
		List<SportsCategory> sportsCategory = sportsCategoryService.getAllCategory();
		mv.addObject("sportsCategory", sportsCategory);
		mv.addObject("pagepath", "Place/add.jsp");
		return mv;
	}

	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Place place, HttpServletRequest request,HttpSession session,@PathParam(value="file") MultipartFile file) {
		place.setStatus(1);
		if(!file.isEmpty()) {
			String realPath = request.getSession().getServletContext().getRealPath("/");
			String filename = FilesUtil.saveFile(file,realPath,"/upload/places/");
			place.setPic(filename);
		}
		placeService.add(place);
		return "redirect:/Place/list.do";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public ModelAndView update(HttpServletRequest request,@PathVariable String id) {
		ModelAndView mv = new ModelAndView("../index");
		mv.addObject("pagepath", "Place/update.jsp");
		Place place = placeService.findPlaceById(id);
		List<SportsCategory> sportsCategory = sportsCategoryService.getAllCategory();
		mv.addObject("place",place);
		mv.addObject("sportsCategory", sportsCategory);
		return mv;
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(Place place, HttpServletRequest request,@PathVariable String id,@PathParam(value="file") MultipartFile file) {
		if(!file.isEmpty()) {
			String realPath = request.getSession().getServletContext().getRealPath("/");
			String filename = FilesUtil.saveFile(file,realPath,"/upload/places/");
			place.setPic(filename);
		}
		placeService.update(place);
		return "redirect:/Place/list.do";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(HttpServletRequest request,@PathVariable String id) {
		placeService.delete(id);
		return "redirect:/User/list.do";
	}
	
}
