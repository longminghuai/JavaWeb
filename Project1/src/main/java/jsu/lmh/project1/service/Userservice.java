package jsu.lmh.project1.service;

import jsu.lmh.project1.entity.Result;
import jsu.lmh.project1.entity.User;
import jsu.lmh.project1.entity.User_Role;
import jsu.lmh.project1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Userservice {
    @Autowired
    UserMapper userMapper;
//    @Autowired
//    private RedisTemplate<String, User> redisTemplate;
    public User query_user(String username){
        return userMapper.findByusername_user(username);
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

    public int register(String username,String password){
        if(userMapper.findByusername_user(username)!=null){
            return 0;
        }
        else{
            userMapper.insertuser(username,password);
            User user = userMapper.findByusername_user(username);
            userMapper.insertcommon(user.getId());
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

    public int deleteuser(Long id) {
        userMapper.deleteuser_role(id);
        return userMapper.deleteuser(id);
    }

    public boolean update_user(User user, Long id) {
        User tuser = userMapper.findByusername_user(user.getUsername());
        if(tuser!=null&&tuser.getId()!=id){
            return false;
        }
//        System.out.println("查到名字了");
        userMapper.update_user(user,id);
        return true;
    }

    public void setrole(Long id) {
        userMapper.setrole(id);
    }

    public List<User_Role> getalluser(String rolename, String username) {
        return userMapper.getalluser(rolename,username);
    }

    public User query_userbyid(Long id) {
        return userMapper.query_userbyid(id);
    }
}
