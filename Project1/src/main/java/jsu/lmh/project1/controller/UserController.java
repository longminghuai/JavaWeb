package jsu.lmh.project1.controller;

import jsu.lmh.project1.entity.Result;
import jsu.lmh.project1.entity.User;
import jsu.lmh.project1.entity.User_Role;
import jsu.lmh.project1.service.Userservice;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.crazycake.shiro.RedisCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
//    @Autowired
//    private RedisCacheManager redisCacheManager;
    @Autowired
    Userservice userservice;
    @PostMapping("login")
    public Result login(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
        Subject subject  = SecurityUtils.getSubject();
        //2封装请求数据到token
        AuthenticationToken token = new UsernamePasswordToken(username,password);
        subject.login(token);
        return Result.success("登陆成功");

    }
    @PostMapping("register")
    public Result Register(@RequestBody Map<String, String> params){
        String username = params.get("username");
        String password = params.get("password");
        System.out.println(username+" "+password);
        int flag=userservice.register(username,password);
        if(flag==1){
            return Result.success(flag);
        }
        else{
            return Result.error("账号已被占用");
        }
    }

    /**
     * 更改角色为vip
     * @return
     */
        @PutMapping("updaterole")
        public Result setrole(@RequestParam("id") Long id){
            userservice.setrole(id);
            // 2. 清除缓存
//            Cache cache = redisCacheManager.getCache("authorizationCacheName");
//            if (cache != null) {
//                cache.clear();
//            }
//            Subject subject = SecurityUtils.getSubject();
//            Session session = subject.getSession();
//            User user = (User) subject.getPrincipal();
//            List<Integer> roleIds = userservice.selroleIdByuserId(user.getId());
//            List<String> roles = new ArrayList<>();
//            for(Integer i:roleIds){
//                System.out.println("roleId :" +i);
//                roles.add(userservice.selrolenameByroleId(i));
//            }
//// 更新会话中的角色信息
//            session.setAttribute("roles", roles);
//            System.out.println("skkss");
//            subject.checkRole("vip");
            return Result.success();
        }
    /**
     * 修改用户信息
     * @return
     */
    @PostMapping("/updateuser")
    public Result updateuser(@RequestBody User user){
//        User user = new User();
//        user.setId(id);
//        user.setUsername(params.get("username"));
//        user.setPassword(params.get("password"));
        System.out.println("进入修改");
        System.out.println(user);
        boolean flag = userservice.update_user(user, user.getId());
        if(flag){
            return Result.success();
        } else {
            return Result.error("主键值重复");
        }
    }
    /**
     * 是否含有会员
     * @param rolename
     * @return
     */
    @GetMapping("hasrole")
    public Result hasrole(String rolename){
        Subject subject = SecurityUtils.getSubject();
        return Result.success(subject.hasRole(rolename));
    }
    @DeleteMapping("deleteuser")
    public Result deleteuser(@RequestParam("id") Long id){
        System.out.println("进入删除");
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        userservice.deleteuser(id);
        if(user.getId()==id){
            return Result.success("注销成功");
        }
        else{
            return Result.success("删除成功");
        }
    }
    @GetMapping("getNowuser")
    public Result hasresult(){
        Subject subject = SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        User tuser= userservice.query_userbyid(user.getId());
        return Result.success(tuser);
    }

    @GetMapping("getalluser")
    public Result getalluser(String rolename,String username){
        List<User_Role> list=userservice.getalluser(rolename,username);
        return Result.success(list);
    }


}
