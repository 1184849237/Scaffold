package com.lvzuan.meetmanager.exception;

import com.lvzuan.meetmanager.constant.Result;
import com.lvzuan.meetmanager.constant.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Admin
 * @ClassName: RoleTypeEnum
 * @Description: 全局异常处理
 * @Create by: 周鹏程
 * @Date: 2020/12/1 11:05
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result globalExceptionHandler(Exception e) {
        log.error("未知异常", e);
        return Result.error();
    }

    @ExceptionHandler(RuntimeException.class)
    public Result runtimeExceptionHandler(RuntimeException e) {
        log.error("运行时异常", e);
        if(e.getCause()!=null){
            return Result.error(e.getCause().getMessage());
        }
        return Result.error(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("参数错误", e);
        return Result.error(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = QueryTimeoutException.class)
    public Result handleQueryTimeoutException(QueryTimeoutException e) {
        log.error("未知异常", e);
        return Result.error("请求超时");
    }

    @ExceptionHandler({BusinessException.class})
    public Result businessExceptionHandler(Exception e) {
        log.error("业务异常 msg={}", e.getMessage());
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler({TokenException.class})
    public Result tokenExpireExceptionHandler(HttpServletRequest req, Exception e) {
        log.error("TOKEN失效/未认证 RequestURI={}", req.getRequestURI());
        return Result.fail(ResultEnum.UNAUTHORIZED.code, e.getMessage());
    }

    @ExceptionHandler({AccessLimitException.class})
    public Result accessLimitExceptionHandler(HttpServletRequest req, Exception e) {
        log.error("AccessLimitException RequestURI={}", req.getRequestURI());
        return Result.fail("服务正忙，请稍后");
    }
}
