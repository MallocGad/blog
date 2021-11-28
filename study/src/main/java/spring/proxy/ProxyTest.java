package spring.proxy;

import org.junit.Test;
import spring.proxy.handler.JdkEnhanceHandler;
import spring.proxy.service.JustInterface;
import spring.proxy.service.ProxyInstance;
import spring.proxy.service.ProxyService;
import spring.proxy.service.ProxyServiceImpl;

import java.lang.reflect.Proxy;

/**
 * 代理测试类
 *
 * @author: huangtao3
 * @since: 2021/9/2 16:22
 */
public class ProxyTest {
    @Test
    public void jdkProxyTest() throws Throwable {
        JdkEnhanceHandler handler = new JdkEnhanceHandler(ProxyServiceImpl.class);
        // proxy代理的作用和下面这段代码类似。
//        handler.invoke(null,ProxyServiceImpl.class.getMethod("get",String.class,int.class),new Object[]{"name",18});
        ProxyService proxyService = (ProxyService)Proxy.newProxyInstance(ProxyServiceImpl.class.getClassLoader(), new Class[]{ProxyService.class, JustInterface.class}, handler);
        proxyService.get("黄涛",23);
    }
}
