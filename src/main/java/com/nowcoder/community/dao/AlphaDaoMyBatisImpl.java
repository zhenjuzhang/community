package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

//假设为AlphaDaoHibernateImpl的改进版本，需要增加注解“@Primary”以使本类优先起作用
@Primary
@Repository
public class AlphaDaoMyBatisImpl implements AlphaDao{
    @Override
    public String select() {
        return "MyBatis";
    }
}
