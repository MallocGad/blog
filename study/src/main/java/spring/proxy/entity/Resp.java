package spring.proxy.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 响应对象
 *
 * @author: huangtao3
 * @since: 2021/9/2 16:10
 */
@Getter
@Setter
@ToString
public class Resp {
    private String msg;
    private Integer code;
    private String data;
}
