package top.huangtao.blog.common.config.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * TODO des
 *
 * @author: huangtao3
 * @since: 2022/3/15 17:40
 */
public class MyInterceptor implements HandlerInterceptor {
    public boolean preHandle(){
        System.out.println("前置处理");
        return true;
    }
}
