package spring.proxy.handler;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类是通过这个Handler的invoke来调用的
 *
 * @author: huangtao3
 * @since: 2021/9/2 13:58
 */
@Slf4j
public class JdkEnhanceHandler implements InvocationHandler {
    private Object target;

    public JdkEnhanceHandler(Class<?> clazz) {
        try {
            this.target = clazz.newInstance();
        } catch (InstantiationException e) {
            log.error("InstantiationException", e);
        } catch (IllegalAccessException e) {
            log.error("IllegalAccessException", e);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before(method.getName(), args);
        Object result = method.invoke(target, args);
        after(result);
        log.info("proxy class={}",proxy.getClass());
        return result;
    }

    public void before(String method, Object[] args) {
        log.info("开始调用对象：{},方法：{},参数：{}", target, method, args);
    }

    public void after(Object result) {
        log.info("结束调用对象：{},响应结果{}", target, result);
    }
}
