package jsu.lmh.project1.entity;

import lombok.Data;

/**
 * 专业
 */
@Data
public class Major {
    Long id;//专业代码
    String spname;//专业名称
    String level3_name;//学科门类
    String level2_name;//专业类
    String level1_name;//专业层次
    String degree;//学位
    Integer fivesalaryavg;//平均薪资
    Integer boy_rate;//男生占比
    Integer girl_rate;//女生占比
    String limit_year;//修学年数
}
