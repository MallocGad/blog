package spring.aop.targ;

/**
 * aop-target
 * @author: huangtao3
 * @since: 2021/12/8 15:10
 */
public class DefaultEchoService implements EchoService {

    @Override
    public String echo(String message) {
        return message;
    }
}
