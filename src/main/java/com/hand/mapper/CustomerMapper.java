package com.hand.mapper;

import com.hand.dto.Customer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CustomerMapper {
    /**
     * 删除用户对象
     */
     int deleteCustomer(Short  customerId);

     int insertCustomer(Customer customer);

    List<Customer> selectAllCustomers(Customer customer);

    int updateCustomer(Customer customer);

 }