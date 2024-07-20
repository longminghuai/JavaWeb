package jsu.lmh.project1.entity;

import lombok.Data;

@Data
public class User_Role {
    Long user_id;
    Long role_id;
    String username;
    String rolename;
}
