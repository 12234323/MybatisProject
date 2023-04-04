package org.example.mapper;

import org.example.pojo.Student;
import org.example.pojo.Teacher;
import org.example.pojo.User;

import java.util.List;

public interface StudentMapper {
    //多对一，多个学生对一个老师，association关联
    public List<Student> queryAll();

    //一对多，一个老师对应多个学生
    public List<Teacher> queryAllTeacher();
}
