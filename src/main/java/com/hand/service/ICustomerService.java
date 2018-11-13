package com.hand.service;

import com.github.pagehelper.Page;
import com.hand.dto.Address;
import com.hand.dto.Customer;

import java.util.List;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/8/21 11:30
 * @Description:
 */
public interface ICustomerService {

 public Page<Customer> getCustomerPage(Customer customer, int pageNum, int pageSize);

 public Customer getCustomer(Customer customer);

 public boolean updataCustomer(Customer customer);

 public boolean addCustomer(Customer customer);

 public boolean deleteCustomer(Short customerId);



}
