package com.hand.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hand.dto.Customer;
import com.hand.mapper.CustomerMapper;
import com.hand.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/8/21 11:30
 * @Description:
 */
@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public Page<Customer> getCustomerPage(Customer customer, int pageNum, int pageSize){

        PageHelper.startPage(pageNum, pageSize);
        Page<Customer> customerPage = (Page<Customer>) customerMapper.selectAllCustomers(customer);
        return customerPage;

    }

    @Override
    public Customer getCustomer(Customer customer) {
        List<Customer> customerList =  customerMapper.selectAllCustomers(customer);
        if (customerList != null){
            return  customerList.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public boolean updataCustomer(Customer customer) {
        customer.setLastUpdate(new Date());
       int result = customerMapper.updateCustomer(customer);
        if (result>0){
            return  true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean addCustomer(Customer customer) {
        customer.setCreateDate(new Date());
        int result = customerMapper.insertCustomer(customer);
        if (result>0){
            return  true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deleteCustomer(Short customerId) {
        int result = customerMapper.deleteCustomer(customerId);
        if (result>0){
            return  true;
        }
        return false;
    }


}
