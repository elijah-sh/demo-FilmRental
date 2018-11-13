package com.hand.mapper;

import com.hand.dto.Address;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressMapper {
    int deleteAddress(Short addressId);

    int insertAddress(Address record);

    Address selectAddress(Short addressId);

    List<Address> selectAllAddress();

    int updateAddress(Address record);
}