package com.kong.service.impl;

import com.kong.enums.Constant;
import com.kong.enums.SexEnum;
import com.kong.mapper.UsersMapper;
import com.kong.pojo.Users;
import com.kong.pojo.bo.UserBO;
import com.kong.service.UserService;
import com.kong.utils.DateUtil;
import com.kong.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    public UsersMapper usersMapper;
    @Resource
    private Sid sid;

    // 查询数据使用Supports
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Example userExample = new Example(Users.class);
        Example.Criteria userExampleCriteria = userExample.createCriteria();

        userExampleCriteria.andEqualTo("username", username);

        Users result = usersMapper.selectOneByExample(userExample);

        return result != null;
    }

    // 更改数据使用REQUIRED
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBO userBO) {
        // 分布式等大型项目不使用自增长类型，数据量过大
        String userId = sid.nextShort();

        Users user = new Users();
        user.setId(userId);
        user.setUsername(userBO.getUsername());

        try {
            user.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        user.setNickname(userBO.getUsername());
        user.setFace(Constant.USER_FACE);
        user.setBirthday(DateUtil.stringToDate("1900-01-01"));
        user.setSex(SexEnum.secret.getType());

        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());

        usersMapper.insert(user);

        return user;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Users queryUserForLogin(String username, String password){
        Example userExample = new Example(Users.class);
        Example.Criteria userExampleCriteria = userExample.createCriteria();

        userExampleCriteria.andEqualTo("username", username);
        userExampleCriteria.andEqualTo("password", password);

        Users result = usersMapper.selectOneByExample(userExample);

        return result;
    }
}
