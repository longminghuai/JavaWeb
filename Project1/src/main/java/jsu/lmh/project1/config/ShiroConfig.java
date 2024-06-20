package jsu.lmh.project1.config;

import jsu.lmh.project1.Realm.MyRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //配置realm
    @Bean(name="myRealm")
    public MyRealm myRealm(){
        return  new MyRealm();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManage") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //过滤器规则
        Map<String,String> filterChainDefinitionMap=new LinkedHashMap<>();
        filterChainDefinitionMap.put("/login","anon");
//        filterChainDefinitionMap.put("/go_home","anon");
//        filterChainDefinitionMap.put("/reg","anon");
//        filterChainDefinitionMap.put("/go_reg","anon");
//        filterChainDefinitionMap.put("/user/all","perms[delet]");
//        filterChainDefinitionMap.put("/load","roles[system]");
        filterChainDefinitionMap.put("/*","authc");
//        //这里定义用户未认证时跳转的路径
        shiroFilterFactoryBean.setLoginUrl("/");
//        //这里是用户登录成功后跳转的路径
//        shiroFilterFactoryBean.setSuccessUrl("/go_home");
//        //这里是用户没有访问权限跳转的路径
        shiroFilterFactoryBean.setUnauthorizedUrl("/home");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    //    将realm加入管理器中
    @Bean(name="securityManage")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("myRealm") MyRealm myRealm){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();

        defaultWebSecurityManager.setRealm((Realm) myRealm);


        return defaultWebSecurityManager;
    }
}
