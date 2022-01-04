package spring.aop.pointcut;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;

/**
 * 切点
 * aop-pointcut
 *
 * @author: huangtao3
 * @since: 2021/12/8 15:16
 */
public interface Pointcut {
    /**
     * 通过类过滤
     */
    ClassFilter getClassFilter();

    /**
     * 通过方法过滤
     */
    MethodMatcher getMethodMather();

}
