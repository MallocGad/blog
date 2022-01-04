package spring.aop.targ;

/**
 *
 * @author: huangtao3
 * @since: 2021/12/8 16:02
 */
public class DefaultSayName implements SayName{
    @Override
    public String getName() {
        return "I am service";
    }
}
