package com.kong.service.impl;

import com.kong.enums.YesOrNo;
import com.kong.mapper.UserAddressMapper;
import com.kong.pojo.UserAddress;
import com.kong.pojo.Users;
import com.kong.pojo.bo.AddressBO;
import com.kong.service.AddressService;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    private UserAddressMapper userAddressMapper;
    @Resource
    private Sid sid;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<UserAddress> queryAll(String userId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        return userAddressMapper.select(userAddress);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addNewUserAddress(AddressBO addressBO) {
        // 1. 判断当前用户是否存在地址，如果没有，则新增为默认地址
        int isDefault = 0;
        List<UserAddress> addressList = this.queryAll(addressBO.getUserId());
        if (addressList == null || addressList.isEmpty()) {
            isDefault = 1;
        }
        // 2. 保存地址到数据库
        UserAddress newAddress = new UserAddress();
        BeanUtils.copyProperties(addressBO, newAddress);

        String addressId = sid.nextShort();
        newAddress.setId(addressId);
        newAddress.setIsDefault(isDefault);
        newAddress.setCreatedTime(new Date());
        newAddress.setUpdatedTime(new Date());

        userAddressMapper.insert(newAddress);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUserAddress(AddressBO addressBO) {
        String addressId = addressBO.getAddressId();

        UserAddress pendingAddress = new UserAddress();
        BeanUtils.copyProperties(addressBO, pendingAddress);
        pendingAddress.setId(addressId);
        pendingAddress.setUpdatedTime(new Date());

        Example userAddressExample = new Example(UserAddress.class);
        Example.Criteria userExampleCriteria = userAddressExample.createCriteria();
        userExampleCriteria.andEqualTo("id", addressId);

        userAddressMapper.updateByExampleSelective(pendingAddress, userAddressExample);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUserAddress(String userId, String addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(addressId);
        userAddress.setUserId(userId);

        userAddressMapper.delete(userAddress);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUserAddressToBeDefault(String userId, String addressId) {
        // 1.查找默认地址，设置为不默认
        UserAddress queryAddress = new UserAddress();
        queryAddress.setUserId(userId);
        queryAddress.setIsDefault(YesOrNo.YES.getType());

        List<UserAddress> addressList = userAddressMapper.select(queryAddress);
        for (UserAddress ua : addressList) {
            ua.setIsDefault(YesOrNo.NO.getType());
            Example userAddressExample = new Example(UserAddress.class);
            Example.Criteria userExampleCriteria = userAddressExample.createCriteria();
            userExampleCriteria.andEqualTo("id", ua.getId());
            userExampleCriteria.andEqualTo("userId", ua.getUserId());
            userAddressMapper.updateByExampleSelective(ua, userAddressExample);
        }

        // 2.根据地址id修改为默认的地址
        UserAddress defaultAddress = new UserAddress();
        defaultAddress.setId(addressId);
        defaultAddress.setUserId(userId);
        defaultAddress.setIsDefault(YesOrNo.YES.getType());

        Example defaultAddressExample = new Example(UserAddress.class);
        Example.Criteria defaultExampleCriteria = defaultAddressExample.createCriteria();
        defaultExampleCriteria.andEqualTo("id", addressId);
        defaultExampleCriteria.andEqualTo("userId", userId);
        userAddressMapper.updateByExampleSelective(defaultAddress, defaultAddressExample);
    }
}
