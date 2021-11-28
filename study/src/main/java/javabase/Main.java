package javabase;

import org.junit.Test;

import java.util.function.Function;

/**
 *
 * @author: huangtao3
 * @since: 2021/7/17 13:53
 */
public class Main {
    Function<Integer,Integer> function;
    @Test
    public void test(){
        function = t->t+1;
        System.out.println(function.apply(1));
    }
}
