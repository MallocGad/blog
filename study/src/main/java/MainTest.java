import abstracttest.ChinaSouthPlant;
import org.junit.Test;

import java.util.Random;

/**
 * TODO des
 *
 * @author: huangtao3
 * @since: 2021/6/5 10:56
 */
public class MainTest {
    @Test
    public void test1(){
//        ChinaSouthPlant plant = new ChinaSouthPlant();
//        plant.fly();
        for (int i = 0; i < 100; i++) {
            int nextInt = new Random().nextInt(100);
            System.out.println(nextInt);
        }
    }
}
