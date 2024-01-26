package com.micro.service.galaxycore.common;

/**
 * @author ray
 * @date 2019/7/11 19:41
 */

public enum ResponseCode {
    //成功
    SUCCESS(0, "SUCCESS"),
    //失败
    ERROR(1, "ERROR"),
    //更新token
    REFRESH_TOKEN(2, "REFRESH_TOKEN"),
    //需要登陆
    NEED_LOGIN(3, "需要登陆");

    private final int code;
    private final String desc;


    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
