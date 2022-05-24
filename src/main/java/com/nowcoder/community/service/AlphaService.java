//项目依赖注入整体流程：由controller处理浏览器的请求，该过程中会调用业务组件service处理当前业务，业务组件又调用dao访问数据库（向service注入dao）。三者之间相互依赖，此依赖关系可通过依赖注入实现
//简单业务组件，通过容器管理此Bean(创建、初始化、销毁)
package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service//业务组件注解
//@Scope("prototype")//表示多个实例，没有本注解时为单例(单例较为常用)
public class AlphaService {

    //向service注入dao
    @Autowired
    private AlphaDao alphaDao;//注入后就可以调用了

    public AlphaService(){
        System.out.println("实例化AlphaService（构造器）");
    }

    @PostConstruct//表示此初始化方法在构造之后调用
    public void init(){
        System.out.println("初始化AlphaService");
    }

    @PreDestroy//表示在销毁对象之前(程序结束之前)调用，可以释放某些资源
    public void destroy(){
        System.out.println("销毁AlphaService");
    }

    //模拟查询业务
    public String find(){
        return alphaDao.select();
    }
}
