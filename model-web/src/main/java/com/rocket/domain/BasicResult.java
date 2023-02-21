package com.rocket.domain;

import com.alibaba.fastjson.JSON;

import java.util.Objects;

public class BasicResult<T> {

    private T data;
    public Boolean success;

    /**
     * 返回状态码
     */
    private String code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 异常堆栈
     */
    private String fullStackTrace;

    private final static String SUCCESS_CODE = "200";
    private final static String DEFAULT_FAIL_CODE = "-1";

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return Objects.equals(BasicResult.SUCCESS_CODE, this.code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFullStackTrace() {
        return fullStackTrace;
    }

    public void setFullStackTrace(String fullStackTrace) {
        this.fullStackTrace = fullStackTrace;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 成功
     *
     * @return
     */
    public static BasicResult success() {
        BasicResult basicResult = new BasicResult();
        basicResult.setCode(SUCCESS_CODE);
        basicResult.setSuccess(true);
        return basicResult;
    }

    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BasicResult<T> success(T data) {
        BasicResult<T> basicResult = new BasicResult<>();
        basicResult.setCode(SUCCESS_CODE);
        basicResult.setData(data);
        basicResult.setSuccess(true);
        return basicResult;
    }

    /**
     * 成功
     *
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> BasicResult<T> success(T data, String message) {
        BasicResult<T> basicResult = new BasicResult<>();
        basicResult.setCode(SUCCESS_CODE);
        basicResult.setMessage(message);
        basicResult.setData(data);
        basicResult.setSuccess(true);
        return basicResult;
    }


    /**
     * 成功
     *
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> BasicResult<T> success(T data, String message, Boolean success) {
        BasicResult<T> basicResult = new BasicResult<>();
        basicResult.setCode(SUCCESS_CODE);
        basicResult.setMessage(message);
        basicResult.setData(data);
        basicResult.setSuccess(success);
        return basicResult;
    }


    /**
     * 失败
     *
     * @param code
     * @param <T>
     * @return
     */
    static <T> BasicResult<T> fail(String code, Throwable throwable) {
        BasicResult<T> basicResult = new BasicResult<>();
        basicResult.setCode(code);
        basicResult.setMessage(throwable.getMessage());
        basicResult.setFullStackTrace(JSON.toJSONString(throwable));
        basicResult.setSuccess(false);
        return basicResult;
    }

    /**
     * 失败
     *
     * @param code
     * @param <T>
     * @return
     */
    public static <T> BasicResult<T> fail(String code, String message) {
        BasicResult<T> basicResult = new BasicResult<>();
        basicResult.setCode(code);
        basicResult.setMessage(message);
        basicResult.setSuccess(false);
        return basicResult;
    }


    /**
     * 失败
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> BasicResult<T> fail(String message) {
        BasicResult<T> basicResult = new BasicResult<>();
        basicResult.setCode(DEFAULT_FAIL_CODE);
        basicResult.setMessage(message);
        basicResult.setSuccess(false);
        return basicResult;
    }

    /**
     * 失败
     *
     * @param throwable
     * @param <T>
     * @return
     */
    static <T> BasicResult<T> fail(Throwable throwable, Boolean isFull) {
        BasicResult<T> basicResult = new BasicResult<>();
        basicResult.setCode("500");
        basicResult.setMessage("系统出错啦");
        basicResult.setSuccess(false);
        if (isFull) {
            basicResult.setFullStackTrace(JSON.toJSONString(throwable));
        } else {
            basicResult.setFullStackTrace(throwable.getMessage());
        }
        return basicResult;
    }
}
