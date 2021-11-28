package top.huangtao.blog.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 黄涛
 */
@SpringBootApplication
@ComponentScan(basePackages = {"top.huangtao.*","spring.lifecycle"})
public class BlogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogServiceApplication.class, args);
	}

}
