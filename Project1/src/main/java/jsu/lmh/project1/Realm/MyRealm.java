package jsu.lmh.project1.Realm;

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

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进入授权...");
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        System.out.println(user.getUsername());

        List<Integer> roleIds = userservice.selroleIdByuserId(user.getId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        for (Integer id : roleIds) {
            System.out.println("roleId :" + id);
            roles.add(userservice.selrolenameByroleId(id));

            List<Integer> premIds = userservice.selpremIdByroleId(id);

            for (Integer pId : premIds) {
                System.out.println("premId :" + pId);
                permissions.add(userservice.selpremnameBypremId(pId));
            }
        }
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("doget load");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        System.out.println(username + " ss " + usernamePasswordToken.getUsername());
        User user = userservice.query_user(username);
        SimpleAuthenticationInfo simpleAuthenticationInfo = null;
        if (user != null) {
            simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), "MyRealm");
        }
        return simpleAuthenticationInfo;
    }

    public void clearCachedAuthorizationInfo(String username) {
        PrincipalCollection principals = getAvailablePrincipal(username);
        if (principals != null) {
            super.clearCachedAuthorizationInfo(principals);
        }
    }

    public void clearCachedAuthenticationInfo(String username) {
        PrincipalCollection principals = getAvailablePrincipal(username);
        if (principals != null) {
            super.clearCachedAuthenticationInfo(principals);
        }
    }

    public void clearCache(String username) {
        PrincipalCollection principals = getAvailablePrincipal(username);
        if (principals != null) {
            super.clearCache(principals);
        }
    }

    private PrincipalCollection getAvailablePrincipal(String username) {
        User user = userservice.query_user(username);
        if (user != null) {
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName()).getPrincipals();
        }
        return null;
    }
}
