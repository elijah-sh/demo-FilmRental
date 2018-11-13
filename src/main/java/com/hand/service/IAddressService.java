package com.hand.service;

import com.hand.dto.Address;
import com.hand.dto.Customer;

import java.util.List;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/8/24 22:49
 * @Description:
 */
public interface IAddressService {
    /**
     * 获取所有地址信息
     * @return
     */
    public List<Address> getAllAddress();
}
