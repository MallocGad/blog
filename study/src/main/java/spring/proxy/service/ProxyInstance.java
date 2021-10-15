package spring.proxy.service;

import spring.proxy.entity.Resp;

/**
 * 需要代理的类-无接口
 *
 * @author: huangtao3
 * @since: 2021/9/2 16:44
 */
public class ProxyInstance {
    public Resp get() {
        Resp resp = new Resp();
        resp.setCode(0);
        resp.setData("数据");
        resp.setMsg("成功");
        return resp;
    }
}
