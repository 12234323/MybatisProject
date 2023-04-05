package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    //查询所有用户
    List<User> queryAll();

    //修改密码,泛型为object，方便我们键值对，有不同类型的值。
    int updatePwd(Map<String,Object> map);

    //模糊查询
    @Select("select * from user where name like \"_\"#{name}")
    List<User> queryUserByLike(String name);

    //插入用户
    int insertUser(User user);

    //分页,不能直接传入两个参数，否则报错。
    List<User> queryUserByLimit(Map<String,Object> map);

    //分页，采用注解形式,当有多个参数时，应该使用@Param参数，否则报错
    //只有一个参数可以不用写@Param
    @Select("select * from user limit #{start},#{pagesize}")
    List<User> queryUserByLimit1(@Param("start") int start, @Param("pagesize") int pagesize);

    //动态SQL
    List<User> queryAllByDynamicSql(int id);

    //动态SQL，choose ，when ,otherwise
    User queryAllByDynamicSql1(Map<String,Object> map);

    //foreach语句，对集合进行遍历
    List<User> queryAllByDynamicSql3(List<Integer> idlist );

}
