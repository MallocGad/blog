package javabase;

import org.junit.Test;

import java.util.function.Function;

/**
 *
 * @author: huangtao3
 * @since: 2021/7/17 13:53
 */
public class Main {
    Function<String,Integer> function;
    @Test
    public void test(){
        System.out.println(function.apply("a"));
    }
}
