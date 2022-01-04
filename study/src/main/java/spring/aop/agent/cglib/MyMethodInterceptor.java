package spring.aop.agent.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * cglib动态代理
 *
 * @author: huangtao3
 * @since: 2021/4/22 9:27
 */
public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("参数：" + Arrays.toString(objects));
        System.out.println("方法：" + method);
        // 这里是真正的调用目标方法，返回值为目标方法的返回值
        Object invoke = methodProxy.invokeSuper(o, objects);
        System.out.println("invoke:" + invoke);
        return invoke;
    }
}
