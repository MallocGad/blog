package abstracttest;

/**
 * TODO des
 *
 * @author: huangtao3
 * @since: 2021/6/5 10:06
 */
public interface Fly {
    int MAX_HEIGHT = 1000;

    /**
     * jdk8 接口默认方法
     */
    default void fly() {
        System.out.println("可以飞");
    }

    /**
     * 是否是动物
     *
     * @return true：是，false：不是
     */
    boolean isAnimal();
}
