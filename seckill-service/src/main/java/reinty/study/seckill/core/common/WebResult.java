package reinty.study.seckill.core.common;


import reinty.study.seckill.core.common.exception.ExceptionCodeEnum;

import java.io.Serializable;

/**
 * 前端封装结果类
 */

public class WebResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int code;

    private final String msg;

    private T data;


    private WebResult(ResultEnum resultEnum) {
        this.code = resultEnum.code;
        this.msg = resultEnum.msg;
    }

    private WebResult(ResultEnum resultEnum, String msg) {
        this.code = resultEnum.code;
        this.msg = msg;
    }

    private WebResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static WebResult failResult() {
        return new WebResult(ResultEnum.FAIL);
    }

    public static <T> WebResult<T> failResult(ExceptionCodeEnum codeEnum) {
        return new WebResult<>(codeEnum.code, codeEnum.message, null);
    }

    public static WebResult failResult(String msg) {
        return new WebResult<>(ResultEnum.FAIL, msg);
    }

    public static<T> WebResult<T> failResult(int code, String msg, T date) {
        return new WebResult<>(code, msg, date);
    }

    public static WebResult sucessResult() {
        return new WebResult(ResultEnum.SUCCESS);
    }

    public static<T> WebResult sucessResult(T date) {
        return new WebResult<>(ResultEnum.SUCCESS.code, null, date);
    }

    public static<T> WebResult<T> sucessResult(T date, String msg) {
        return new WebResult<>(ResultEnum.SUCCESS.code, msg, date);
    }

    public int getCode() {
        return code;
    }

    public boolean isSuccess(){
        return code == ResultEnum.SUCCESS.code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
