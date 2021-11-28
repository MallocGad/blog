package top.huangtao.blog.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.lifecycle.LifeCycleService;

import javax.annotation.Resource;


/**
 * TODO des
 *
 * @author: huangtao3
 * @since: 2021/3/23 11:48
 */
@Slf4j
@RestController
public class HelloWorld {

    @GetMapping("/hello")
    public String hello(){
        log.info("哈哈哈哈");
        return "你好";
    }
}
