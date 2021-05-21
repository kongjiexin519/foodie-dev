package com.kong.controller;

import com.kong.enums.ExceptionEnum;
import com.kong.pojo.bo.UserBO;
import com.kong.service.UserService;
import com.kong.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("passport")
@Api(value = "注册登录", tags = {"用于注册登录的相关接口"})
public class PassportController {
    @Autowired
    private UserService userService;

    @GetMapping("/usernameIsExist")
    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
    public IMOOCJSONResult usernameIsExist(@RequestParam String username) {
        //1. 判断用户名不为空
        if (StringUtils.isBlank(username)) {
            return IMOOCJSONResult.errorMsg(ExceptionEnum.NEED_USER_NAME.getMsg());
        }
        //2. 查找注册的用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return IMOOCJSONResult.errorMsg(ExceptionEnum.NAME_EXISTED.getMsg());
        }
        //3. 请求成功
        return IMOOCJSONResult.ok();
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    public IMOOCJSONResult register(@RequestBody UserBO userBO) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();

        // 0. 判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)) {
            return IMOOCJSONResult.errorMsg(ExceptionEnum.NEED_USERNAME_PASSWORD.getMsg());
        }
        // 1. 查询用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return IMOOCJSONResult.errorMsg(ExceptionEnum.NAME_EXISTED.getMsg());
        }
        // 2. 密码长度不能少于6位
        if (password.length() < 6) {
            return IMOOCJSONResult.errorMsg(ExceptionEnum.PASSWORD_TOO_SHORT.getMsg());
        }
        // 3. 判断两次密码是否一致
        if (!password.equals(confirmPassword)) {
            return IMOOCJSONResult.errorMsg(ExceptionEnum.PASSWORD_INCONSISTENT.getMsg());
        }
        // 4. 实现注册
        userService.createUser(userBO);

        return IMOOCJSONResult.ok();
    }
}
