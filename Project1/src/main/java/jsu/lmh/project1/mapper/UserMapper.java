package jsu.lmh.project1.mapper;

import jsu.lmh.project1.entity.Result;
import jsu.lmh.project1.entity.User;
import jsu.lmh.project1.entity.User_Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    User findByusername_user(String username);

    int insertuser(String username,String password);

    List<Integer> selroleIdByuserId(Long id);

    String selrolenameByroleId(Integer id);

    List<Integer> selpremIdByroleId(Integer id);

    String selpremnameBypremId(Integer id);

    @Delete("delete from user where id=#{id}")
    int deleteuser(Long id);

    @Delete("delete from user_role where user_id=#{id}")
    int deleteuser_role(Long id);
    @Update("update user set username=#{user.username},password=#{user.password},fk=#{user.fk},sk=#{user.sk},tk=#{user.tk},`rank`=#{user.rank},score=#{user.score} where id=#{id}")
    boolean update_user(User user, Long id);

    @Update("update user_role set role_id =2 where user_id=#{id} ")
    void setrole(Long id);

    @Insert("insert into user_role (user_id, role_id) values (#{id}, 1)")
    void insertcommon(Long id);
    List<User_Role> getalluser(String rolename, String username);

    @Select("select * from user where id=#{id}")
    User query_userbyid(Long id);
}
