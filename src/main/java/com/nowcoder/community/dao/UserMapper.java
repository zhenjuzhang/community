package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    //查询User
    User selectById(int id);//根据id查用户
    User selectByName(String username);
    User selectByEmail(String email);

    //增加用户
    int insertUser(User user);

    //修改
    int updateStatus(int id, int status);
    int updateHeader(int id, String headerUrl);
    int updatePassword(int id, String password);
}
