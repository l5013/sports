package com.lal.sp.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lal.sp.bean.JsonData;
import com.lal.sp.bean.Organization;
import com.lal.sp.service.OrganizationService;

@Controller
@RequestMapping("/Organization")
public class OrganizationAction {
	
	@Autowired
	private OrganizationService organizationService;
	
	
	@RequestMapping(value="register",consumes = "application/json",method=RequestMethod.POST)
	@ResponseBody
	public JsonData register(@RequestBody Organization organization) {
			JsonData data;
			try {
				organizationService.register(organization);
				data = new JsonData("true");
			} catch (Exception e) { 
				e.printStackTrace();
				data = new JsonData("false");
			}
		return data;
	}

}
