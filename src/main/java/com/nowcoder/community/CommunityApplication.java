//此程序为main程序，是运行项目整体的程序
package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//程序入口注解，注解标识的类为配置文件
public class CommunityApplication {

	//底层启动了tomcat，且自动创建了Spring容器，自动扫描某些包下的某些bean并将bean装到容器中
	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}

}
