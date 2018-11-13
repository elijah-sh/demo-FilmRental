package com.hand.service.impl;

import com.hand.dto.Address;
import com.hand.mapper.AddressMapper;
import com.hand.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/8/24 22:50
 * @Description:
 */
@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public List<Address> getAllAddress() {
        List<Address> addressList = addressMapper.selectAllAddress();

            if(addressList!=null){
                return addressList;
            }
        return   null;
    }
}
