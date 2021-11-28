package spring.proxy.service;

import spring.proxy.entity.Resp;

/**
 * jdk必须要接口才能代理:
 * 其原理是通过ProxyGenerator.generateProxyClass方法动态生成一个继承proxy并实现该接口的代理类,这个代理类
 * 实现该接口的方法
 *
 * @author: huangtao3
 * @since: 2021/9/2 13:52
 */
public interface ProxyService {
    /**
     * 被代理方法
     */
    Resp get(String name,int age);
}
