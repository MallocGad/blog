package spring.aop;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO des
 *
 * @author: huangtao3
 * @since: 2021/12/8 16:16
 */
public class Main1 {
    public static void main(String[] args) {
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPattern(".*put.*");
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.printf("当前存放的key为%s，值为%s\n",args[0],args[1]);
            }
        });
        ProxyFactory proxyFactory = new ProxyFactory(new HashMap<>());
        proxyFactory.addAdvisor(advisor);
        Map<String, String> proxyMap = (Map<String, String>) proxyFactory.getProxy();

        proxyMap.put("a","a");
    }
}
