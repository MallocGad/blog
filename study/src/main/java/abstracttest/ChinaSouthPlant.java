package abstracttest;

/**
 * TODO des
 *
 * @author: huangtao3
 * @since: 2021/6/5 10:19
 */
public class ChinaSouthPlant implements Fly {
    @Override
    public boolean isAnimal() {
        return true;
    }

    @Override
    public void fly() {
        System.out.println("需要装满油才可以飞");
    }
}
