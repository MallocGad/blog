package abstracttest;

import org.junit.Test;

/**
 * TODO des
 *
 * @author: huangtao3
 * @since: 2021/6/5 10:54
 */
public class MainTest {
    @Test
    public void test1(){
        ChinaSouthPlant plant = new ChinaSouthPlant();
        System.out.println(plant.isAnimal());
        plant.fly();
    }
}
