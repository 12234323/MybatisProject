package org.example.pojo;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private int id;
    private String name;
    private int t_id;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", t_id=" + t_id +
                '}';
    }
}
