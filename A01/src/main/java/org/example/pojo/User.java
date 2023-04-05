package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Alias("user") 用注解给类起别名
public class User implements Serializable {
    private int id;
    private String name;
    private String password;
}
