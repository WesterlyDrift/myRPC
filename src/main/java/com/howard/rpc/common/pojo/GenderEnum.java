package com.howard.rpc.common.pojo;

public enum GenderEnum {


    MALE(1),
    FEMALE(2),
    OTHER(3);
    private final int code;
    GenderEnum(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
}
