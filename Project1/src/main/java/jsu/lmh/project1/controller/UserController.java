package jsu.lmh.project1.controller;

import jsu.lmh.project1.entity.Admin;
import jsu.lmh.project1.entity.Result;
import jsu.lmh.project1.entity.User;
import jsu.lmh.project1.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    Userservice userservice;
    @PostMapping("login")
    public Result login(@RequestBody Map<String, String> params){
        String username = params.get("username");
        String password = params.get("password");
        Admin admin = userservice.login_admin(username, password);
        User user = userservice.login_user(username, password);
        if(admin!=null){
            return Result.success(admin);
        }
        else if(user!=null){
            return Result.success(user);
        }
        else{
            return Result.error("账号或密码错误");
        }
    }
    @PostMapping("register")
    public Result Register(String username,String password){
        System.out.println(username+" "+password);
        int flag=userservice.register(username,password);
        if(flag==1){
            return Result.success(flag);
        }
        else{
            return Result.error("账号已被占用");
        }
    }

//    @DeleteMapping("delete_user")
//    public Result delete_user(Long id){
//
//    }
//
//    @DeleteMapping("delete_admin")
//    public Result delete_user(Long id){
//
//    }
//    @PutMapping("updata_user")
//    public Result updata_user(){
//
//    }
//
//    @PutMapping("updata_admin")
//    public Result updata_admin(){
//
//    }
}
