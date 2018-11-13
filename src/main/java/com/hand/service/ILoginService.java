package com.hand.service;

import java.util.List;
import java.util.Map;

import com.hand.dto.Customer;

/**
	 *  登录
	 * @author ShenShuaihu
	 *
	 */
public interface ILoginService {
	
	/**
	 *   登录
	 * @param Customer  用户名和密码 或者手机号和密码
	 * @return 返回完整的用户信息
	 */
	public Customer login(Customer customer);


}
