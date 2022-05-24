package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

//实现AlphaDao接口的类，本实现类即为一个Bean。能够被容器扫描的Bean需满足：①在同一包"com.nowcoder.community"下，②含有注解
@Repository//访问数据库的注解
//每个Bean默认的名字为首字母小写的类名，可通过“@Repository("name")”自定义
public class AlphaDaoHibernateImpl implements AlphaDao {//实现接口
    @Override
    public String select() {//实现接口的方法
        return "Hibernate";
    }
}
