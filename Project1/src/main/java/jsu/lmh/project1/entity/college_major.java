package jsu.lmh.project1.entity;

import lombok.Data;

/**
 * 院校-专业关系类
 */
@Data
public class college_major {
    Long collegeid;
    Long majorid;
    String collegename;
    String majorname;
    int num;//计划招生人数
    int score;//录取最低分
    int rank;//最低录取位次
    int probability;//录取概率
}
