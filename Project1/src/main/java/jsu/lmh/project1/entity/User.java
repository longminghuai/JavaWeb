package jsu.lmh.project1.entity;

import lombok.Data;

import java.util.List;

@Data
public class User {
    Long id;
    String username;
    String password;
    String fk;//第一科目
    String sk;
    String tk;
    int score;
    int rank;
}
