package org.example.aspect;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Description 日志切片
 *
 * @author zhoupengcheng
 * @date 2021/03/31
 **/
@Component
@Aspect
@Order
@Slf4j
public class CompassAspect {

    /**
     * @description 切入点
     * @author Bob
     * @date 2020/8/25
     */
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController) @within(org.springframework.stereotype.Controller) @within(com.lvzuan.meetmanager.annotation.ControllerMethodLog)")
    public void logPointCut() {

    }
    /**
     * @description Advice方式
     * @author Bob
     * @date 2020/8/25
     */
    @Around("logPointCut()")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String url = request.getRequestURI();
        String ip = request.getRemoteAddr();
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        String resourceId = IdUtil.simpleUUID();
        MDC.put("resourceId",resourceId);
        log.info("##################ip:{},resourceId:{},url:{},class:{},method:{},args:{}", ip,resourceId, url, className, methodName, handlerParameter(pjp));
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long runTime = System.currentTimeMillis() - start;
        String returnStr = JSON.toJSONString(result);
        returnStr = StrUtil.subPre(returnStr,200);
        log.info("##################resourceId:{},url:{},runTime:{},result:{}",resourceId,url, runTime, returnStr);
        return result;
    }

    /**
     * @description 参数组装
     * @author Bob
     * @date 2020/8/25
     */
    private String handlerParameter(ProceedingJoinPoint point) {
        StringBuilder stringBuilder = new StringBuilder();
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = point.getArgs();
        int i = 0;
        for (Object pojo : args) {
            stringBuilder.append(parameterNames[i]).append(":").append(pojo).append(",");
        }
        return stringBuilder.toString();
    }
}