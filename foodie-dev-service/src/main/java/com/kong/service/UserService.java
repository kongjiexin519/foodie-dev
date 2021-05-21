package com.kong.service;

import com.kong.pojo.Users;
import com.kong.pojo.bo.UserBO;

public interface UserService {
    /**
     * 判断用户名是否存在
     * @param username 用户名
     * @return 存在true 不存在 false
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     * @param userBO 前端传入的用户数据
     * @return 用户对象
     */
    Users createUser(UserBO userBO);

    /**
     * 检索用户名和密码是否匹配，用于登录
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     */
    Users queryUserForLogin(String username, String password);
}
