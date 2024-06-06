package jsu.lmh.project1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OurClass {
    public Long id;//班级编号
    public String class_name;//班级名称
    public String class_teacher;//班主任
}
