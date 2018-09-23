package com.syw.blog.ptool;

import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;

public class ResponseMessage<T> implements Serializable {


    @JsonView(GeneralViews.INormalView.class)
    private Integer result;

    @JsonView(GeneralViews.INormalView.class)
    private String message;

    @JsonView(GeneralViews.INormalView.class)
    private T data;

    public ResponseMessage(){}

    public ResponseMessage(Integer result, String message, T data) {
        super();
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
