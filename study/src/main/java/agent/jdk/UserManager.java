package agent.jdk;

/**
 * 动态代理案列
 *
 * @author: huangtao3
 * @since: 2021/4/16 16:55
 */
public interface UserManager {
    /**
     * 新增用户
     */
    void addUser(String userName,String pwd);

    /**
     * 删除
     */
    void deleteUser(String userName);
}
