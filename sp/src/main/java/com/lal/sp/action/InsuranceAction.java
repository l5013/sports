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
import com.lal.sp.bean.Insurance;
import com.lal.sp.bean.PageBean;
import com.lal.sp.bean.Place;
import com.lal.sp.bean.SportsCategory;
import com.lal.sp.service.AdvertiserService;
import com.lal.sp.service.InsuranceService;
import com.lal.sp.service.PlaceService;
import com.lal.sp.service.SportsCategoryService;
import com.lal.sp.util.Conv;
import com.lal.sp.util.FilesUtil;


@Controller
@RequestMapping("/Insurance")
public class InsuranceAction {
	
	@Autowired
	private InsuranceService insuranceService;
	
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
		int totalCount = insuranceService.listInsurancesCount(params);
		List<Insurance> recordList = insuranceService.listInsurances(params,currentPage,Conv.pageSize);
		ModelAndView mv = new ModelAndView("../index");
		mv.addObject("currentPage", currentPage);
		mv.addObject("pageBean", new PageBean(currentPage, Conv.pageSize, recordList, totalCount));
		mv.addObject("pagepath", "Insurance/list.jsp");
		return mv;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("../index");
		mv.addObject("pagepath", "Insurance/add.jsp");
		return mv;
	}

	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Insurance insurance, HttpServletRequest request,HttpSession session,@PathParam(value="file") MultipartFile file) {
		insurance.setStatus(1);
		if(!file.isEmpty()) {
			String realPath = request.getSession().getServletContext().getRealPath("/");
			String filename = FilesUtil.saveFile(file,realPath,"/upload/insurance/");
			insurance.setPic(filename);
		}
		insuranceService.add(insurance);
		return "redirect:/Insurance/list.do";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public ModelAndView update(HttpServletRequest request,@PathVariable String id) {
		ModelAndView mv = new ModelAndView("../index");
		mv.addObject("pagepath", "Insurance/update.jsp");
		Insurance insurance = insuranceService.findInsuranceById(id);
		mv.addObject("insurance",insurance);
		return mv;
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(Insurance insurance, HttpServletRequest request,HttpSession session,@PathParam(value="file") MultipartFile file) {
		if(!file.isEmpty()) {
			String realPath = request.getSession().getServletContext().getRealPath("/");
			String filename = FilesUtil.saveFile(file,realPath,"/upload/insurance/");
			insurance.setPic(filename);
		}
		insuranceService.update(insurance);
		return "redirect:/Insurance/list.do";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(HttpServletRequest request,@PathVariable String id) {
		insuranceService.delete(id);
		return "redirect:/Insurance/list.do";
	}
	
}
