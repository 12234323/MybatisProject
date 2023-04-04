package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Alias("user") 用注解给类起别名
public class User {
    private int id;
    private String name;
    private String pwd;
}
