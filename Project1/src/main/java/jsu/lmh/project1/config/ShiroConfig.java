//package jsu.lmh.project1.config;
//
//import jsu.lmh.project1.Realm.MyRealm;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.realm.Realm;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.util.ThreadContext;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.servlet.Filter;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@Configuration
//public class ShiroConfig {
//    //配置realm
//    @Bean(name="myRealm")
//    public MyRealm myRealm(){
//        return  new MyRealm();
//    }
//
//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManage") DefaultWebSecurityManager securityManager){
//        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
//        filters.put("authc",new CORSFilter());
////        filters.put("asyncFilter", new AsyncFilter()); // 添加异步过滤器
//        //过滤器规则
//        Map<String,String> filterChainDefinitionMap=new LinkedHashMap<>();
//        filterChainDefinitionMap.put("/login","anon");
////        filterChainDefinitionMap.put("/go_home","anon");
////        filterChainDefinitionMap.put("/reg","anon");
////        filterChainDefinitionMap.put("/go_reg","anon");
////        filterChainDefinitionMap.put("/user/all","perms[delet]");
//        filterChainDefinitionMap.put("/college","roles[vip]");
//        filterChainDefinitionMap.put("/*","authc");
////        //这里定义用户未认证时跳转的路径
//        shiroFilterFactoryBean.setLoginUrl("/");
////        //这里是用户登录成功后跳转的路径
////        shiroFilterFactoryBean.setSuccessUrl("/go_home");
////        //这里是用户没有访问权限跳转的路径
//        shiroFilterFactoryBean.setUnauthorizedUrl("/home");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;
//    }
//
//    //    将realm加入管理器中
//    @Bean(name="securityManage")
//    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("myRealm") MyRealm myRealm){
//        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
//
//        defaultWebSecurityManager.setRealm((Realm) myRealm);
//        SecurityUtils.setSecurityManager(defaultWebSecurityManager);
//        return defaultWebSecurityManager;
//    }
//
//    /**
//     * @description:开启代码权限注解支持
//     */
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManage") SecurityManager securityManager){
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }
//
//    /**
//     * @description:解决权限注解不生效问题
//     */
//    @Bean
//    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
//        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
//        /**
//         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
//         * 在@Controller注解的类的方法中加入@RequiresRole等shiro注解，会导致该方法无法映射请求，导致返回404。
//         * 加入这项配置能解决这个bug
//         */
//        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
//        return defaultAdvisorAutoProxyCreator;
//    }
//
//    /**
//     *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
//     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
//     * @return
//     */
//    @Bean
//    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
//        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        advisorAutoProxyCreator.setProxyTargetClass(true);
//        return advisorAutoProxyCreator;
//    }
//}
