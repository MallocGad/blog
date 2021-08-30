package spring.lifecycle;

import org.springframework.stereotype.Component;

/**
 * TODO des
 *
 * @author: huangtao3
 * @since: 2021/8/25 16:58
 */
@Component
public class MineBean {
    private String message="aaa";
    public void test(){
        System.out.println(message);
    }
}
