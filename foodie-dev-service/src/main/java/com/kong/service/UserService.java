package com.kong.service;

public interface UserService {
    /**
     * 判断用户名是否存在
     * @param username 用户名
     * @return 存在true 不存在 false
     */
    public boolean queryUsernameIsExist(String username);
}
