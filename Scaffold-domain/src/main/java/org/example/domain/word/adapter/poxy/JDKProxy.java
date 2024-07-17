package org.example.domain.word.adapter.poxy;

import org.example.domain.word.adapter.WordModuleAdapter;
import org.example.domain.word.adapter.impl.TextAdapter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @ClassName: JDKProxy
 * @Description: TODO
 * @Create by: 周鹏程
 * @Date: 2024/7/8 17:23
 */
public class JDKProxy {

    public static <T> T getProxy(Class<T> interfaceClass, Object object)  {
        InvocationHandler handler = new JDKInvocationHandler(object);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?>[] classes = interfaceClass.getInterfaces();
        return (T) Proxy.newProxyInstance(classLoader, new Class[]{classes[0]}, handler);
    }

    public static void main(String[] args) throws Exception {

    }
}
