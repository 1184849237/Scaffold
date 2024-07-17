package org.example.types.model;

import lombok.Data;
import org.example.types.common.Constants;

import java.io.Serializable;

/**
 * @author Admin
 * @ClassName: Result
 * @Description: 返回对象
 * @Create by: 周鹏程
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 状态编码
     */
    private String code;
    /**
     * 信息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;

    private Result(String code, String msg) {
        this.code = code;
        this.data = null;
        this.msg = msg;
    }

    private Result(String code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static <T> Result<T> data(T data) {
        return data(data, Constants.ResponseCode.SUCCESS.getInfo());
    }

    public static <T> Result<T> data(T data, String msg) {
        return data(Constants.ResponseCode.SUCCESS.getCode(), data, msg);
    }

    public static <T> Result data(String code, T data, String msg) {
        return new Result(code, data, msg);
    }

    public static <T> Result<T> success() {
        return success(Constants.ResponseCode.SUCCESS.getInfo());
    }

    public static <T> Result<T> success(String msg) {
        return new Result<>(Constants.ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> fail() {
        return fail(Constants.ResponseCode.UN_ERROR.getInfo());
    }

    public static <T> Result<T> fail(String msg) {
        return fail(Constants.ResponseCode.UN_ERROR.getCode(), msg);
    }

    public static <T> Result<T> fail(String code, String msg) {
        return new Result<>(code, msg);
    }
    public static <T> Result<T> error() {
        return new Result<>(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
    }
    public static <T> Result<T> error(String msg) {
        return new Result<>(Constants.ResponseCode.UN_ERROR.getCode(), msg);
    }

    public static <T> Result<T> error(Constants.ResponseCode responseCode) {
        return new Result<>(responseCode.getCode(), responseCode.getInfo());
    }

    public static <T> Result<T> status(boolean flag) {
        return flag ? success() : fail();
    }
}