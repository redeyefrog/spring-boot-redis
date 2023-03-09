package com.redeyefrog.enums;

public enum StatusCode {

    SUCCESS("0000", "Success"),

    ADD_REDIS_FAIL("0010", "Add Record to Redis Fail"),

    UNKNOWN("9999", "Unknown Error");

    private String code;

    private String desc;

    StatusCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
