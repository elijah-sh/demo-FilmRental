package com.hand.service.impl;

import com.hand.dto.Customer;
import com.hand.mapper.CustomerMapper;
import com.hand.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/8/24 16:04
 * @Description:
 */
@Service
public class LoginServiceImpl implements ILoginService {
   @Autowired
   private CustomerMapper customerMapper;
    @Override
    public Customer login(Customer customer) {

        List<Customer> result = customerMapper.selectAllCustomers(customer);
        if(result.size() != 0) {
            return result.get(0);
        }
        return null;
    }
}
