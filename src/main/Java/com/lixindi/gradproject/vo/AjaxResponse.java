package com.lixindi.gradproject.vo;

import com.lixindi.gradproject.utils.Status;

/**
 * Created by lixindi on 2017/3/8.
 */
public class AjaxResponse<T> {
    private int status;
    private T data;

    public AjaxResponse() {
    }

    public AjaxResponse(Status status, T data) {
        this.status = status.getStatus();
        this.data = data;
    }

    public AjaxResponse(T data) {
        this(Status.OK, data);
    }

    public AjaxResponse(Status status) {
        this(status, null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
