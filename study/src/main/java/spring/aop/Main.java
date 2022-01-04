package spring.aop;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import spring.aop.targ.DefaultEchoService;
import spring.aop.targ.DefaultSayName;
import spring.aop.targ.EchoService;
import spring.aop.targ.SayName;

/**
 * TODO des
 *
 * @author: huangtao3
 * @since: 2021/12/8 15:37
 */
public class Main {
    public static void main(String[] args) {
        // pointcut写的一大坨等同于下面这个
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("echo");
        SayName sayName = new DefaultSayName();
        EchoService echoService = new DefaultEchoService();

        DelegatingIntroductionInterceptor interceptor = new DelegatingIntroductionInterceptor(sayName);
        DefaultIntroductionAdvisor advisor = new DefaultIntroductionAdvisor(interceptor, SayName.class);

        ProxyFactory proxyFactory = new ProxyFactory(echoService);
        proxyFactory.addAdvisor(advisor);

        EchoService proxyEchoService = (EchoService) proxyFactory.getProxy();
        System.out.println(proxyEchoService.echo("hello world!"));

        SayName proxySayName = (SayName) proxyFactory.getProxy();
        System.out.println(proxySayName.getName());

    }
}
