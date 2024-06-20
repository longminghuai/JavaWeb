package jsu.lmh.project1.service;

import jsu.lmh.project1.entity.Admin;
import jsu.lmh.project1.entity.User;
import jsu.lmh.project1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Userservice {
    @Autowired
    UserMapper userMapper;
    public User query_user(String username){
        return userMapper.findByusername_user(username);
    }
    public Admin query_admin(String username){
        return userMapper.findByusername_admin(username);
    }
    public User login_user(String username,String password){
        User user=userMapper.findByusername_user(username);
        if(user!=null){
            if(user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    public Admin login_admin(String username, String password){
        Admin admin = userMapper.findByusername_admin(username);
        if(admin!=null){
            if(admin.getPassword().equals(password)){
                return admin;
            }
        }
        return null;
    }

    public int register(String username,String password){
        if(userMapper.findByusername_user(username)!=null||userMapper.findByusername_admin(username)!=null){
            return 0;
        }
        else{
            userMapper.insertuser(username,password);
            return 1;
        }
    }

    public List<Integer> selroleIdByuserId(Long id) {
        return userMapper.selroleIdByuserId(id);
    }

    public String selrolenameByroleId(Integer id) {
        return userMapper.selrolenameByroleId(id);
    }

    public List<Integer> selpremIdByroleId(Integer id) {
        return userMapper.selpremIdByroleId(id);
    }

    public String selpremnameBypremId(Integer id) {
        return userMapper.selpremnameBypremId(id);
    }
}
