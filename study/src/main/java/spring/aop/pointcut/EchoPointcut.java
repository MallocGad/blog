package spring.aop.pointcut;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import spring.aop.targ.EchoService;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * TODO des
 *
 * @author: huangtao3
 * @since: 2021/12/8 15:23
 */
public class EchoPointcut implements Pointcut{

    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                return EchoService.class.isAssignableFrom(clazz);
            }
        };
    }

    @Override
    public MethodMatcher getMethodMather() {
        return new MethodMatcher() {
            @Override
            public boolean matches(Method method, Class<?> aClass) {
                return "echo".equals(method.getName())
                        && method.getParameterTypes().length == 1
                        && Objects.equals(String.class,method.getParameterTypes()[0]);
            }

            @Override
            public boolean isRuntime() {
                return false;
            }

            @Override
            public boolean matches(Method method, Class<?> aClass, Object... objects) {
                return false;
            }
        };
    }
}
