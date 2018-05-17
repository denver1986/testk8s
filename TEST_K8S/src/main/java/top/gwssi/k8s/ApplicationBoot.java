package top.gwssi.k8s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
//@EnableAuthorizationServer
// 如果不指定ComponentScan的扫描路径，则会扫描启动类@SpringBootApplication所在包下的类
// @ComponentScan(basePackages＝cn.gwssi)
public class ApplicationBoot {

	@RequestMapping("/")
	public String index() {
		return "长城软件";
	}

	public static void main(String[] args) {
		SpringApplication.run(ApplicationBoot.class, args);
	}
}