package com.lixindi.gradproject.utils;

/**
 * Created by lixindi on 2017/3/8.
 */
public enum Status {
    OK(0, "操作成功"),
    ERROR(1, "出现错误"),
    DUPLICATE_KEY(2,"重复的key"),
    WRONG_TOKEN(3,"token错误"),
    ID_EXSITS(4,"id已存在")
    ;

    private int status;
    private String message;

    Status(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
