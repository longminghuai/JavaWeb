package jsu.lmh.project1.entity;

import java.util.List;

public class User {
    Long id;
    String username;
    String password;
    int role;//角色类型
    String fk;//第一科目
    String sk;
    String tk;
    int score;
    int rank;
    List<Volunteer> vlist;
}
