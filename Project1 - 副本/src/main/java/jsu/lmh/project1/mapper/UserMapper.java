package jsu.lmh.project1.mapper;

import jsu.lmh.project1.entity.Admin;
import jsu.lmh.project1.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findByusername_user(String username);

    Admin findByusername_admin(String username);

    int insertuser(String username,String password);

    List<Integer> selroleIdByuserId(Long id);

    String selrolenameByroleId(Integer id);

    List<Integer> selpremIdByroleId(Integer id);

    String selpremnameBypremId(Integer id);
}
