package jsu.lmh.project1.Realm;

import jsu.lmh.project1.entity.Admin;
import jsu.lmh.project1.entity.Result;
import jsu.lmh.project1.entity.User;
import jsu.lmh.project1.service.Userservice;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    Userservice userservice;
    /**
     * 授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进入授权...");
        //系统角色
        Set<String> roles=new HashSet<>();
//        roles.add("system");
        Set<String> permissions=new HashSet<>();

        Subject subject= SecurityUtils.getSubject();
        User user=(User)subject.getPrincipal();
        System.out.println(user.getUsername());

        List<Integer> roleIds=userservice.selroleIdByuserId(user.getId());
        //为当前用户添加角色和权限
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
//        info.addStringPermission("user:all");

        for(Integer id:roleIds){
            System.out.println("roleId :" +id);
            roles.add(userservice.selrolenameByroleId(id));

            List<Integer> premIds=userservice.selpremIdByroleId(id);

            for(Integer pId:premIds){
                System.out.println("premId :"+pId);
                permissions.add(userservice.selpremnameBypremId(pId));
            }
        }
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 认证方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("doget load");
        //这里强转的类型不一定要是UsernamePasswordToken ，具体要看你在登录接口中所传的对象类型
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username=usernamePasswordToken.getUsername();
        System.out.println(username+" ss "+usernamePasswordToken.getUsername());
        Admin admin = userservice.query_admin(username);
        User user = userservice.query_user(username);
        SimpleAuthenticationInfo simpleAuthenticationInfo = null;
        if(admin!=null){
            simpleAuthenticationInfo= new SimpleAuthenticationInfo(admin, admin.getPassword(), "MyRealm");

        }
        else if(user!=null){
            simpleAuthenticationInfo= new SimpleAuthenticationInfo(user, user.getPassword(), "MyRealm");
        }
        return simpleAuthenticationInfo;
    }
}
