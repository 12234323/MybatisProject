package org.example.mapper;

import org.example.pojo.User;

import java.util.List;

public interface UserMapper {
    public List<User> queryAll();
    public void insertUser(User user);
    public void deleteUser(int id);
}
