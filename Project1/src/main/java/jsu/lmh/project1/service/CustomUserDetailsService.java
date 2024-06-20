//package jsu.lmh.project1.service;
//
//import jsu.lmh.project1.entity.Admin;
//import jsu.lmh.project1.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private Userservice userservice;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Admin admin = userservice.query_admin(username);
//        User user = userservice.query_user(username);
//
//        if (admin == null && user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        if (admin != null) {
//            // Add roles and permissions for admin
//            List<Integer> roleIds = userservice.selroleIdByuserId(admin.getId());
//            for (Integer roleId : roleIds) {
//                authorities.add(new SimpleGrantedAuthority("ROLE_" + userservice.selrolenameByroleId(roleId)));
//                List<Integer> permIds = userservice.selpremIdByroleId(roleId);
//                for (Integer permId : permIds) {
//                    authorities.add(new SimpleGrantedAuthority(userservice.selpremnameBypremId(permId)));
//                }
//            }
//            return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(), authorities);
//        } else {
//            // Add roles and permissions for user
//            List<Integer> roleIds = userservice.selroleIdByuserId(user.getId());
//            for (Integer roleId : roleIds) {
//                authorities.add(new SimpleGrantedAuthority("ROLE_" + userservice.selrolenameByroleId(roleId)));
//                List<Integer> permIds = userservice.selpremIdByroleId(roleId);
//                for (Integer permId : permIds) {
//                    authorities.add(new SimpleGrantedAuthority(userservice.selpremnameBypremId(permId)));
//                }
//            }
//            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
//        }
//    }
//}
