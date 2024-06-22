package jsu.lmh.project1.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User  implements Serializable {
    Long id;
    String username;
    String password;
    String fk;//第一科目
    String sk;
    String tk;
    int score;
    int rank;
}
