package javabase;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ht
 * @Date: Create in 10:38 2021/8/15
 * @Describe:
 * @Last_change:
 */
public class JavaCollections {
    public static void main(String[] args) {
        // 使用集合前最好初始化，如果不初始化首次会初始化为10
//        sun.misc.Launcher
        List<String> list = new ArrayList<>(2);
        list.add("test");
    }
}
