package com.kong.enums;

public enum ExceptionEnum {
    NEED_USER_NAME("用户名不能为空"),
    NEED_PASSWORD("密码不能为空"),
    NAME_EXISTED("用户名已经存在");

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
