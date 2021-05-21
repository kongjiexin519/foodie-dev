package com.kong.service.impl;

import com.kong.mapper.UsersMapper;
import com.kong.pojo.Users;
import com.kong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    public UsersMapper usersMapper;

    // 查询数据使用Supports
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Example userExample = new Example(Users.class);
        Example.Criteria userExampleCriteria = userExample.createCriteria();

        userExampleCriteria.andEqualTo("username", username);

        Users result = usersMapper.selectOneByExample(userExample);

        if (result!=null) {
            System.out.println("111");
            System.out.println(result.getUsername());
        }else {
            System.out.println("2222");
        }


        return result != null;
    }
}
