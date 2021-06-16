package agent.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 *
 * @author: huangtao3
 * @since: 2021/4/16 17:02
 */
public class JdkProxy implements InvocationHandler {

    private Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK动态代理，调用前代理。");
        Object res = method.invoke(target, args);
        System.out.println("JDK动态代理，调动后代理。");
        return res;
    }

    private Object getJDKProxy(Object target){
        this.target=target;
        System.out.println(this.getClass());
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    public static void main(String[] args) {
        JdkProxy proxy = new JdkProxy();
        UserManager userManager = (UserManager) proxy.getJDKProxy(new UserManagerImpl());
        userManager.addUser("黄涛","1212");
    }
}
