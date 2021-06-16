package agent.cglib;

import org.springframework.cglib.proxy.Enhancer;

/**
 * Cglib代理
 *
 * @author: huangtao3
 * @since: 2021/4/22 9:22
 */
public class CglibProxy {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloMethod.class);
        enhancer.setCallback(new MyMethodInterceptor());

        HelloMethod helloMethod = (HelloMethod) enhancer.create();
        helloMethod.sayHello("小明");
        System.out.println("================");
        helloMethod.sayBye("小红");
        System.out.println(helloMethod.hashCode());
    }
}
