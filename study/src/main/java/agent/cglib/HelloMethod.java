package agent.cglib;

/**
 * 被代理目标类
 *
 * @author: huangtao3
 * @since: 2021/4/22 10:00
 */
public class HelloMethod {
    public int sayHello(String name){
        System.out.println("Hello : "+name);
        return 222;
    }
    int sayBye(String name){
        System.out.println("Good Bye:"+name);
        return 333;
    }
}
