package multithread;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * 主类
 *
 * @author: huangtao3
 * @since: 2021/7/15 16:09
 */
public class Main {

    @Test
    public void test() {
        CompletableFuture.runAsync(this::func).whenComplete((r, e) -> {
            System.out.println("执行完毕！");
            System.out.println("r:" + r + "e:" + e);
        });
        System.out.println("aa111a");
        try {
            Thread.sleep(3010);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("aaa");
    }

    public void func() {
        System.out.println("func调用方法开始...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("func调用方法结束...");
    }

    public void func2() {
        System.out.println("func2调用方法开始...");
        System.out.println("func2调用方法结束...");
    }
}
