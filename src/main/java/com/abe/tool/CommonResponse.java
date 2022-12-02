package com.abe.tool;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CommonResponse<T> implements Serializable {

    public Integer code;

    public String msg;

    public T data;

    public CommonResponse() {
    }

    public CommonResponse(Integer code) {
        this.code = code;
    }

    /**
     * @param code
     * @param data
     */
    public CommonResponse(Integer code, T data) {
        super();
        this.code = code;
        this.data = data;
    }

    /**
     * @param code
     * @param msg
     */
    public CommonResponse(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CommonResponse [code=" + this.code + ", msg=" + this.msg + ", data=" + this.data + "]";
    }
}
