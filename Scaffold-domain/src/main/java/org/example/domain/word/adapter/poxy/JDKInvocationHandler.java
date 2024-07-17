package org.example.domain.word.adapter.poxy;

import org.example.domain.utils.ClassLoaderUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName: JDKInvocationHandler
 * @Description: TODO
 * @Create by: 周鹏程
 * @Date: 2024/7/8 17:23
 */
public class JDKInvocationHandler implements InvocationHandler {
    private  Object object;

    public JDKInvocationHandler(Object object) {
        this.object = object;
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        return object.getClass().getMethod(method.getName(),
                ClassLoaderUtils.getClazzByArgs(args)).invoke(object, args);
    }
}
