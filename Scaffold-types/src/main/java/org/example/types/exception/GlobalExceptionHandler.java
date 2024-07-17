package org.example.types.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.types.model.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Description: 全局异常处理
 * @Create by: zhoupengcheng
 * @Date: 2020/12/1 11:05
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<Object> globalExceptionHandler(Exception e) {
        log.error("未知异常", e);
        return Result.error();
    }

    @ExceptionHandler(RuntimeException.class)
    public Result<Object> runtimeExceptionHandler(RuntimeException e) {
        log.error("运行时异常", e);
        if(e.getCause()!=null){
            return Result.error(e.getCause().getMessage());
        }
        return Result.error(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("参数错误", e);
        return Result.error(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler({AccessLimitException.class})
    public Result<Object> accessLimitExceptionHandler(HttpServletRequest req, Exception e) {
        log.error("AccessLimitException RequestURI={}", req.getRequestURI());
        return Result.fail("服务正忙，请稍后");
    }
}
