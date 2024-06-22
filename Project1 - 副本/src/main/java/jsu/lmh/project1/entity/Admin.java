package jsu.lmh.project1.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 管理员
 */
@Data
public class Admin implements Serializable {
    Long id;
    String username;
    String password;
}
