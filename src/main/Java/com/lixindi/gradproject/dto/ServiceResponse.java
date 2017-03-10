package com.lixindi.gradproject.dto;

import com.lixindi.gradproject.utils.Status;

/**
 * Created by lixindi on 2017/3/9.
 */
public class ServiceResponse<T> {
    private Status status;
    private T data;

    public ServiceResponse() {
    }

    public ServiceResponse(Status status, T data) {
        this.status = status;
        this.data = data;
    }

    public ServiceResponse(T data) {
        this(Status.OK, data);
    }

    public ServiceResponse(Status status) {
        this(status, null);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
