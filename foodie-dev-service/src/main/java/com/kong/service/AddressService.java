package com.kong.service;

import com.kong.pojo.UserAddress;
import com.kong.pojo.bo.AddressBO;

import java.util.List;

public interface AddressService {
    // 根据用户id查询用户的收货地址列表
    public List<UserAddress> queryAll(String userId);

    // 用户新增地址
    public void addNewUserAddress(AddressBO addressBO);

    // 用户修改地址
    public void updateUserAddress(AddressBO addressBO);
}
