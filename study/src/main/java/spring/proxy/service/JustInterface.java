package spring.proxy.service;

/**
 * TODO des
 *
 * @author: huangtao3
 * @since: 2021/9/2 17:08
 */
public interface JustInterface {
    void add();
    default void set(){
        System.out.println("aa");
    }
}
