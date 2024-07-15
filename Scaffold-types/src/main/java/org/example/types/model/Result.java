package com.lvzuan.meetmanager.constant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Admin
 * @ClassName: Result
 * @Description: 返回对象
 * @Create by: 周鹏程
 */
@Data
@ApiModel("统一api响应结果封装")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 状态编码
     */
    @ApiModelProperty(value = "状态编码")
    private int code;
    /**
     * 信息
     */
    @ApiModelProperty(value = "消息")
    private String msg;
    /**
     * 数据
     */
    @ApiModelProperty(value = "数据")
    private T data;

    private Result(int code, String msg) {
        this.code = code;
        this.data = null;
        this.msg = msg;
    }

    private Result(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static <T> Result<T> data(T data) {
        return data(data, ResultEnum.SUCCESS.msg);
    }

    public static <T> Result<T> data(T data, String msg) {
        return data(ResultEnum.SUCCESS.code, data, msg);
    }

    public static <T> Result data(int code, T data, String msg) {
        return new Result(code, data, msg);
    }

    public static <T> Result<T> success() {
        return success(ResultEnum.SUCCESS.msg);
    }

    public static <T> Result<T> success(String msg) {
        return new Result<>(ResultEnum.SUCCESS.code, msg);
    }

    public static <T> Result<T> fail() {
        return fail(ResultEnum.FAIL.msg);
    }

    public static <T> Result<T> fail(String msg) {
        return fail(ResultEnum.FAIL.code, msg);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return new Result<>(code, msg);
    }
    public static <T> Result<T> error() {
        return new Result<>(ResultEnum.INTERNAL_SERVER_ERROR.code, ResultEnum.INTERNAL_SERVER_ERROR.msg);
    }
    public static <T> Result<T> error(String msg) {
        return new Result<>(ResultEnum.INTERNAL_SERVER_ERROR.code, msg);
    }

    public static <T> Result<T> error(ResultEnum resultEnum) {
        return new Result<>(resultEnum.code, resultEnum.msg);
    }

    public static <T> Result<T> status(boolean flag) {
        return flag ? success() : fail();
    }
}