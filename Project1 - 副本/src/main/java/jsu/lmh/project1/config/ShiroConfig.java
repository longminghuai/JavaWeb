//package jsu.lmh.project1.config;
//
//import jsu.lmh.project1.Realm.MyRealm;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.realm.Realm;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
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
//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManage") DefaultWebSecurityManager securityManager){
//        System.out.println("进入shirofilter...");
//        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
////        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
////        filters.put("authc",new CORSFilter());
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
//    //    将realm加入管理器中
//    @Bean(name="securityManage")
//    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("myRealm") MyRealm myRealm){
//        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
//
//        defaultWebSecurityManager.setRealm(myRealm);
////        SecurityUtils.setSecurityManager(defaultWebSecurityManager);
//        return defaultWebSecurityManager;
//    }
//
//    //配置realm
//    @Bean(name="myRealm")
//    public MyRealm myRealm(){
//        return  new MyRealm();
//    }
//
//
////    /**
////     * @description:开启代码权限注解支持
////     */
////    @Bean
////    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManage") SecurityManager securityManager){
////        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
////        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
////        return authorizationAttributeSourceAdvisor;
////    }
////
////    /**
////     * @description:解决权限注解不生效问题
////     */
////    @Bean
////    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
////        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
////        /**
////         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
////         * 在@Controller注解的类的方法中加入@RequiresRole等shiro注解，会导致该方法无法映射请求，导致返回404。
////         * 加入这项配置能解决这个bug
////         */
////        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
////        return defaultAdvisorAutoProxyCreator;
////    }
////
////    /**
////     *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
////     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
////     * @return
////     */
////    @Bean
////    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
////        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
////        advisorAutoProxyCreator.setProxyTargetClass(true);
////        return advisorAutoProxyCreator;
////    }
////
////    @Bean
////    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
////        return new LifecycleBeanPostProcessor();
////    }
//}

package jsu.lmh.project1.config;
import jsu.lmh.project1.Realm.MyRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Configuration
public class ShiroConfig {
    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        log.info("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //注意过滤器配置顺序 不能颠倒
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
        filterChainDefinitionMap.put("/logout", "logout");
        // 首页
        filterChainDefinitionMap.put("/", "anon");
        // 登录
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/**", "CORSFilter");
//        filterChainDefinitionMap.put("/college","roles[vip]");
        //配置 shiro 默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //自定义过滤器
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("CORSFilter", new CORSFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 将自己的用户认证验证方式加入容器
     */
    @Bean
    public MyRealm hopeShiroRealm() {
        return new MyRealm();
    }

    /**
     * 权限管理，配置主要是Realm的管理认证 和session管理
     *
     * @param redisSessionManager redis session共享
     * @param redisCacheManager   redis 缓存
     */
    @Bean
    public SecurityManager securityManager(
            @Qualifier("redisSessionManager") DefaultWebSessionManager redisSessionManager,
            @Qualifier("redisCacheManager") RedisCacheManager redisCacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(hopeShiroRealm());
        securityManager.setSessionManager(redisSessionManager);
        securityManager.setCacheManager(redisCacheManager);
        SecurityUtils.setSecurityManager(securityManager);
//        Map<>
//        ThreadContext.setResources();
        return securityManager;
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisManager redisManager(RedisInfo redisConfig) {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisConfig.getHost());
        redisManager.setPort(redisConfig.getPort());
        redisManager.setTimeout(redisConfig.getTimeout());
        return redisManager;
    }

    /**
     * redisSession相关配置
     * 自定义session持久化
     * 为啥session也要持久化？
     *             重启应用，用户无感知，可以继续以原先的状态继续访问
     *       注意点：
     *             DO对象需要实现序列化接口 Serializable
     *             logout接口和以前一样调用，请求logout后会删除redis里面的对应的key,即删除对应的token
     */
    @Bean
    public RedisSessionDAO redisSessionDAO(RedisManager redisManager) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager);
        redisSessionDAO.setKeyPrefix("HOPE_SHIRO_SESSION:"); // key
        redisSessionDAO.setExpire(1800); // 过期时间，设置为与Shiro的Session超时时间一致
        return redisSessionDAO;
    }


    /**
     * session的管理 用redis实现session共享
     */
    @Bean
    public DefaultWebSessionManager redisSessionManager(RedisSessionDAO redisSessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO);
        return sessionManager;
    }

    /**
     * 配置具体cache实现类RedisCacheManager
     * 为什么要使用缓存:
     * 缓存组件位于SecurityManager中,在HopeShiroRealm数据域中,由于授权方法中每次都要查询数据库,性能受影响,因此将数据缓存起来,提高查询效率
     * 除了使用Redis缓存,还能使用shiro-ehcache
     */
    @Bean
    public RedisCacheManager redisCacheManager(RedisManager redisManager) {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        return redisCacheManager;
    }


    /**
     * 加入注解的使用，不加入这个注解不生效
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 注册全局异常处理
     */
//    @Bean(name = "exceptionHandler")
//    public HandlerExceptionResolver handlerExceptionResolver() {
//        return new HopeExceptionHandler();
//    }

        /**
     * @description:解决权限注解不生效问题
     */
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole等shiro注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug
         */
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}
