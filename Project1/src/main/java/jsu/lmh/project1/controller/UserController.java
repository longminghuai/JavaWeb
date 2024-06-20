package jsu.lmh.project1.controller;

import jsu.lmh.project1.entity.Admin;
import jsu.lmh.project1.entity.Result;
import jsu.lmh.project1.entity.User;
import jsu.lmh.project1.service.Userservice;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    Userservice userservice;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("login")
    public Result login(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return Result.success("登录成功");
        } catch (AuthenticationException e) {
            return Result.error("登录失败: " + e.getMessage());
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
