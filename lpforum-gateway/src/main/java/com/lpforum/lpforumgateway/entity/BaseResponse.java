package com.lpforum.lpforumgateway.entity;

import java.io.Serializable;

public class BaseResponse implements Serializable {

    private static final long serialVersionUID = 2970073413867466262L;

    private String code;
    private String msg;
    private String data;
    private Long timestamp;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
