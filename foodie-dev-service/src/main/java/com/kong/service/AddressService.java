package com.kong.service;

import com.kong.pojo.UserAddress;

import java.util.List;

public interface AddressService {
    // 根据用户id查询用户的收货地址列表
    public List<UserAddress> queryAll(String userId);
}
