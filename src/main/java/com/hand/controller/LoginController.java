package com.hand.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import com.hand.dto.Customer;
import com.hand.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import tk.mybatis.mapper.entity.Example;

@Controller  
 public class LoginController {
	@Autowired
	private ILoginService loginService;
	
 	 private static Logger log = Logger.getLogger(LoginController.class.getName());


	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping("/login.do")
	public String login() {
		return "login";
	}

	/***
	 * 验证用户登录
	 * @param name
	 * @param password
	 * @param session
	 * @return  登录结果
	 */
	@RequestMapping("/checkLogin.do")
	@ResponseBody
	public Map<String,Object> checkLogin(String name, String password, HttpSession session){

		System.out.println(name+"|||"+password);
		Map<String,Object> map = new HashMap<>();
		Customer customer = new Customer();
		customer.setFirstName(name);

		customer = loginService.login(customer);
		if(customer == null) {
			map.put("flag", false);
			map.put("data", "用户信息不存在");
			System.out.println(map);
			return map;
		}
		
		// 验证账号 密码 登录。
		if (name.equals(customer.getFirstName()) && password.equals(customer.getLastName())) {
			
			session.setAttribute("user", customer);
			map.put("flag", true);
			log.info(" 登陆成功"+customer.getFirstName()+"--"+session.getAttribute("user"));
			return map;
			
		} else {
			map.put("flag", false);
			map.put("data", "密码错误");
			return map;
		}
		 
	}
	
	/**
	 *  登录成功 进入用户管理主页面
	 * @return
	 */
	@RequestMapping("/index.do")
	public String index() {
		 return "customer/customerInfo";
	}


	/**
	 *   退出登录 移出session
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout.do")
	public String  logout(HttpSession session){
		session.removeAttribute("user");
		session.invalidate();  //将session设置为失效，一般在退出时使用
		return "login";
	}

	/**
	 * 从session中获取登录用户信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/getLoginName.do")
	@ResponseBody
	public String  getLoginName(HttpSession session){

		Customer customer = (Customer)session.getAttribute("user");
		log.info(" 获取登录用户信息："+customer.getFirstName());
		if (customer != null){
			String name = customer.getFirstName();
			return name;
		}
		return "";
	}

}
