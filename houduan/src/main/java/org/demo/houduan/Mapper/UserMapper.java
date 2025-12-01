package org.demo.houduan.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.demo.houduan.Entity.User;
@Mapper
public interface UserMapper {

    User selectByUserName(String userName);

    void insert(User user);
}
