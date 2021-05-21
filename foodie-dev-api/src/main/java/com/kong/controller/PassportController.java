package com.kong.controller;

import com.kong.enums.ExceptionEnum;
import com.kong.service.UserService;
import com.kong.utils.IMOOCJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("passport")
public class PassportController {
    @Autowired
    private UserService userService;

    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult usernameIsExist(@RequestParam String username) {
        //1. 判断用户名不为空
        if (StringUtils.isBlank(username)) {
            return IMOOCJSONResult.errorMsg(ExceptionEnum.NEED_USER_NAME.getMsg());
        }
        //2. 查找注册的用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (! isExist) {
            return IMOOCJSONResult.errorMsg(ExceptionEnum.NAME_EXISTED.getMsg());
        }
        //3. 请求成功
        return IMOOCJSONResult.ok();
    }
}
