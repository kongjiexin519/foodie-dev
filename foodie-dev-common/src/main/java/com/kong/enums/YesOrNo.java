package com.kong.enums;

public enum YesOrNo {
    NO(0, "否"),
    YES(1, "是");

    Integer type;
    String value;

    YesOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
