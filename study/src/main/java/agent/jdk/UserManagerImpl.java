package agent.jdk;

/**
 * 实现类
 *
 * @author: huangtao3
 * @since: 2021/4/16 16:58
 */
public class UserManagerImpl implements UserManager {
    @Override
    public void addUser(String userName, String pwd) {
        System.out.println("新增用户：" + userName);
    }

    @Override
    public void deleteUser(String userName) {
        System.out.println("删除用户：" + userName);
    }
}
