package spring.proxy.service;

import spring.proxy.entity.Resp;

/**
 * 被代理接口实现
 *
 * @author: huangtao3
 * @since: 2021/9/2 16:13
 */
public class ProxyServiceImpl implements ProxyService {
    @Override
    public Resp get(String name, int age) {
        System.out.println(name + ":" + age);
        Resp resp = new Resp();
        resp.setCode(0);
        resp.setData("数据");
        resp.setMsg("成功");
        return resp;
    }
}
