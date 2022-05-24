//String中容器的主动获取与使用
package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

@Configuration//表示是一个配置类
public class AlphaConfig {

    @Bean//增加一个第三方的Bean
    public SimpleDateFormat simpleDateFormat(){//方法名就是Bean的名字
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//方法返回的对象将被装配到容器里
    }

}
