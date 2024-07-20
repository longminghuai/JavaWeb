//package jsu.lmh.project1.utils;
//
//import org.apache.catalina.core.ApplicationContext;
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SpringUtil implements ApplicationContextAware {
//    private static ApplicationContext context;
//
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        context = applicationContext;
//    }
//
//
//    public static <T> T getBean(Class<T> beanClass) {
//        return context.getBean(beanClass);
//    }
//
//    public static <T> T getBean(String beanName) {
//        return (T) context.getBean(beanName);
//    }
//
//    @Override
//    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) throws BeansException {
//
//    }
//}
//
