package multithread;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 主类
 *
 * @author: huangtao3
 * @since: 2021/7/15 16:09
 */
public class Main {

    int a = 0;
    int b = 0;
    int c = 0;

    Integer lock1 = new Integer(1);
    Integer lock2 = new Integer(2);

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

    public static volatile int race = 0;

    private static final int THREADS_COUNT = 20;

    public static void increase() {
        race++;
    }

    @Test
    public void testThread() {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increase();
                }
            });
            threads[i].start();
        }

        while (Thread.activeCount() > 1) {
            Thread.yield();
            LockSupport.park();
        }

        System.out.println(race);
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

    @Test
    public void testDoubleThread() {
        Main main = new Main();
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (lock1) {
                    func1();
                }
            }
            System.out.println("A结束");
        });
        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (lock1) {
                    func2();
                }
            }
            System.out.println("B结束");
        });

        threadA.start();

        threadB.start();
        Thread.currentThread().getThreadGroup().list();
        try {
            Thread.activeCount();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    // 如果他们能被并发调用，那么c的值一定不等于a+b;
    public void func1() {
        a++;
        c++;

    }

    public void func2() {
        b++;
        c++;
    }

    @Test
    public void testInterrupt() {
        AtomicInteger a = new AtomicInteger();
        Integer lock1 = new Integer(1);
        Integer lock2 = new Integer(1);
        Thread thread = new Thread(() -> {
            try {
                synchronized (this) {
                    boolean b = Thread.currentThread().isInterrupted();
                    System.out.println("111当前线程interrupt状态为：" + b);
                    System.out.println("111thread的锁信息：" + Thread.holdsLock(this));
//                    wait();
                    Thread.sleep(1000);
                    notify();
                    a.getAndIncrement();
                    boolean b1 = Thread.currentThread().isInterrupted();
                    System.out.println("111后置判断，当前线程状态为：" + b1);
                }
            } catch (Exception e) {
                e.printStackTrace();
                boolean interrupted = Thread.currentThread().isInterrupted();
                System.out.println("111当前线程状态为：" + interrupted);
            }
        });
        Thread thread1 = new Thread(() -> {
            try {
                synchronized (this) {
                    boolean b = Thread.currentThread().isInterrupted();
                    System.out.println("222当前线程interrupt状态为：" + b);
                    System.out.println("222thread的锁信息：" + Thread.holdsLock(this));
                    wait();
                    a.getAndIncrement();
                    boolean b1 = Thread.currentThread().isInterrupted();
                    System.out.println("222后置判断，当前线程状态为：" + b1);
                }
            } catch (Exception e) {
                e.printStackTrace();
                boolean interrupted = Thread.currentThread().isInterrupted();
                System.out.println("222当前线程状态为：" + interrupted);
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                synchronized (this) {
                    boolean b = Thread.currentThread().isInterrupted();
                    System.out.println("333当前线程interrupt状态为：" + b);
                    System.out.println("333thread的锁信息：" + Thread.holdsLock(this));
                    wait();
                    a.getAndIncrement();
                    boolean b1 = Thread.currentThread().isInterrupted();
                    System.out.println("333后置判断，当前线程状态为：" + b1);
                }
            } catch (Exception e) {
                e.printStackTrace();
                boolean interrupted = Thread.currentThread().isInterrupted();
                System.out.println("333当前线程状态为：" + interrupted);
            }
        });
        thread2.setPriority(7);
        thread1.setPriority(1);
        thread.setPriority(10);
        thread2.start();
        thread1.start();
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println("thread线程中断状态：" + thread.isInterrupted());
//        thread.interrupt();
//        System.out.println("中断后thread线程中断状态：" + thread.isInterrupted());
    }

    @Test
    public void testRetLock(){
        ReentrantLock lock = new ReentrantLock();
        if (lock.tryLock()){
            System.out.println("hahahaha");
        }

    }

}
