package jsu.lmh.project1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 学生
 */
public class Student {
    public Long id;
    public String exam_number;
    public  String name;
    public String gender;
    public LocalDate birth_date;
    public String hometown;
    public int entrance_score;

}
