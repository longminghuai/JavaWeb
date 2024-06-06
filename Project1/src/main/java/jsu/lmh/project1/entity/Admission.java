package jsu.lmh.project1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 录取关系
 */
public class Admission {
    public Long id;
    //学生编号
    public Long student_id;
    //大学编号
    public Long college_id;
}
