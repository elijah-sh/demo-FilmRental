package com.hand.controller;


import com.github.pagehelper.Page;
import com.hand.dto.Address;
import com.hand.dto.Customer;
import com.hand.service.IAddressService;
import com.hand.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.logging.Logger;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/8/21 11:50
 * @Description:  客户管理
 */
@Controller
@RequestMapping(value = "/customer" )
public class CustomerController {
    static Logger log = Logger.getLogger(CustomerController.class.getName());

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IAddressService addressService;

    @RequestMapping(value = "/info.do" )
    public String getCustomer () {
         return "customer/customerInfo";
    }

    /**
     *  获取用户列表
     * @param customer
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getCustomerInfo.do" )
    @ResponseBody
    public Map<String,Object> getCustomersList (Customer customer, int pageNum, int pageSize) {

        log.info("查询customer数据 ："+customer.toString());

        Map<String,Object> map = new HashMap();

        Page<Customer> customerPage = customerService.getCustomerPage(customer,pageNum,pageSize);
        if (customerPage!=null) {
            map.put("flag", true);
            List<Customer> customerList = customerPage.getResult();
            int pagePages = customerPage.getPages();
            map.put("customerList", customerList);
            map.put("pagePages", pagePages);
            log.info("查询数据："+customerList.size());
        }else {
            map.put("flag", false);
        }
        return map;
    }

    /**
     * 根据条件查询填个客户
     * @param Customer
     * @return
     */
    @RequestMapping("/getCustomer.do")
    @ResponseBody
    public Map<String,Object> getCustomer(Customer Customer) {

        Map<String,Object> map = new HashMap<>();


        Customer customer = customerService.getCustomer(Customer);
        log.info(Customer.toString()+"  ");

        if(customer != null) {
            map.put("flag", true);
            map.put("data", customer);
            return map;
        }
        map.put("flag", false);
        return map;

    }

    /**
     *  修改客户信息
     * @param Customer
     * @return
     */
    @RequestMapping("/updateCustomer.do")
    @ResponseBody
    public Map<String,Object> updateCustomer(Customer Customer) {

        Map<String,Object> map = new HashMap<>();


        boolean result = customerService.updataCustomer(Customer);
        log.info(Customer.toString()+" ------->  "+result);

        if(result == false) {
            map.put("flag", false);
            return map;
        }

        if(result == true) {
            map.put("flag", true);
            return map;
        }

        return null;

    }

    /**
     * 进入添加页面
     * @return
     */
    @RequestMapping("/create.do")
    public String customerAdd() {
        return "customer/customerAdd";
    }

    /**
     * 执行条件客户操作
     * @param customer
     * @return
     */
    @RequestMapping("/addCustomer.do")
    @ResponseBody
    public Map<String,Object> addCustomer(Customer customer) {

        Map<String,Object> map = new HashMap<>();

        boolean result = customerService.addCustomer(customer);
        log.info(customer.toString()+"  "+result);

        if(result == false) {
            map.put("flag", false);
            return map;
        }

        if(result == true) {
            map.put("flag", true);
            return map;
        }

        return null;

    }

    /**
     * 删除客户
     * @param customerId
     * @return
     */
    @RequestMapping("/deleteCustomer.do")
    @ResponseBody
    public Map<String,Object> deleteCustomer(Short customerId) {

        Map<String,Object> map = new HashMap<>();

        log.info("删除： "+customerId);
        boolean result = customerService.deleteCustomer(customerId);
        log.info(customerId+"  "+result);

        if(result == false) {
            map.put("flag", false);
            return map;
        }

        if(result == true) {
            map.put("flag", true);
            return map;
        }

        return null;

    }

    /**
     * 获取收到地址信息
     * @return
     */
    @RequestMapping("/getAddress.do")
    @ResponseBody
    public Map<String,Object> getAddress() {

        Map<String,Object> map = new HashMap<>();

        List<Address> addressList = addressService.getAllAddress( );
        log.info( " addressList "+addressList.size());

        if(addressList != null) {
             map.put("flag", true);
            map.put("data", addressList);
            return map;
        }

         map.put("flag", true);
        return map;
    }



}
