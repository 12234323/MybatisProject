package org.example.mapper;

import org.example.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    //查询所有用户
    List<User> queryAll();

    //修改密码,泛型为object，方便我们键值对，有不同类型的值。
    int updatePwd(Map<String,Object> map);

    //模糊查询
    List<User> queryUserByLike(String name);
}
