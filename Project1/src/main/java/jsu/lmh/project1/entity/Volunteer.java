package jsu.lmh.project1.entity;

import lombok.Data;

@Data
public class Volunteer {
    Long id;
    Long userid;//用户
    Long user_volunteerid;//用户的第num个志愿
    Long majorid;//专业代号
    String majorname;//专业名称
    Long collegeid;//院校id
    String collegename;//院校名称
    int num;//计划招生人数
    int rank;//最低位次
}
