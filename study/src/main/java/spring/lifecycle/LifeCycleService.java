package spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 生命周期基础
 * <p>通过实验和debug看源码得知，bean实例化过程为：
 * 构造方法-@Value属性-所有processor方法(这里包含内置的processor和自定义的)-@PostConstruct注解方法-InitializingBean方法
 * @author: huangtao3
 * @since: 2021/8/25 13:53
 */
@Component
public class LifeCycleService implements InitializingBean, DisposableBean, ApplicationContextAware, BeanPostProcessor {
    @Value("aaa")
    private String a;
    public LifeCycleService(){
        System.out.println("LifeCycle 构造方法调用");
        System.out.println("=========================================");
    }

    /**
     * 和PreDestroy注解功能类似
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("destroy方法开始调用");
        System.out.println("=========================================");
    }

    /**
     * 和PostConstruct注解功能类似
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet方法开始调用");
        System.out.println("=========================================");
    }

    @PostConstruct
    public void init(){
        System.out.println("init方法开始调用");
        System.out.println("=========================================");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("preDestroy方法开始调用");
        System.out.println("=========================================");
    }


    /**
     * 是通过BeanPostProcessor的创建前增强方法，然后调用的
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext方法开始调用");
        String name = applicationContext.getApplicationName();
        System.out.println(name);
        System.out.println("=========================================");
    }

    /**
     * 实现BeanPostProcessor接口的bean不同于普通的bean，它会优先于普通bean实例化，然后后续注册的bean都会调用这些processor的方法进行增强处理。
     * 另外实现了BeanPostProcessor的bean将不会执行自定义继承了BeanPostProcessor的类的方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization方法开始调用");
        System.out.println(beanName);
        System.out.println("=========================================");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization方法开始调用");
        System.out.println(beanName);
        System.out.println("=========================================");
        return bean;
    }


}