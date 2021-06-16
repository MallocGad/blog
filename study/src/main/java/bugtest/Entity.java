package bugtest;

import lombok.Getter;
import lombok.Setter;

/**
 * 实体
 *
 * @author: huangtao3
 * @since: 2021/5/11 13:55
 */
public class Entity {
    private String name;
    private String age;

    public void setName(String name) {
        this.name = name;
        System.out.println("名字" + name);
    }

    public void setAge(String age) {
        this.age = age;
        System.out.println("年龄" + age);
    }
}
