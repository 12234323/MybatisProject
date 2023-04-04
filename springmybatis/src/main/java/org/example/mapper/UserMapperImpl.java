package org.example.mapper;

import org.apache.ibatis.session.SqlSession;
import org.example.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper{

    @Override
    public List<User> queryAll() {
        //省略了注入的过程
        SqlSession session=getSqlSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        return mapper.queryAll();
    }

    @Override
    public void insertUser(User user) {
        //将插入用户和删除用户放到同一个方法，事务才会生效。
        getSqlSession().getMapper(UserMapper.class).insertUser(user);
        deleteUser(1);
    }

    @Override
    public void deleteUser(int id) {
        getSqlSession().getMapper(UserMapper.class).deleteUser(id);
    }


}
