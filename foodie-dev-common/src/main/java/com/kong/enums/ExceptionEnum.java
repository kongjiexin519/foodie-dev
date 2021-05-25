package com.kong.enums;

public enum ExceptionEnum {
    NEED_USER_NAME("用户名不能为空"),
    NEED_USERNAME_PASSWORD("用户名或密码不能为空"),
    NAME_EXISTED("用户名已经存在"),
    PASSWORD_INCONSISTENT("两次密码输入不一致"),
    PASSWORD_TOO_SHORT("密码长度不能少于6位"),
    CATEGORY_NOT_EXIST("分类不存在");

    String msg;

    ExceptionEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
