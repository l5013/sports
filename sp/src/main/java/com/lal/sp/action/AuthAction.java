package com.lal.sp.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lal.sp.bean.Admin;
import com.lal.sp.service.AuthService;

@Controller
@RequestMapping("/Auth")
public class AuthAction {
	
	@Autowired
	private AuthService authService;
	
	 @RequestMapping(value="/login")
	    public String login(HttpServletRequest request, HttpSession session,Model model){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("username", username);
			params.put("password", password);
			Admin admin = authService.login(params);
			if(admin!=null){
	    		session.setAttribute("loginUser", admin);
			}else{
				request.setAttribute("msg", "账户或密码错误");
				return "forward:/login.jsp";
			}
			return "forward:/index.jsp";
	    }

	 @RequestMapping("/logout")
	    public String logout(HttpSession session){
	    	session.removeAttribute("loginUser");
	    	return "forward:/login.jsp";
	    }
	 
}
