//package jsu.lmh.project1.controller;
//
//import jsu.lmh.project1.entity.Result;
//import org.apache.shiro.authz.AuthorizationException;
//import org.apache.shiro.authz.UnauthorizedException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@ControllerAdvice
//public class PermissionsException {
//    @ResponseBody
//    @ExceptionHandler(UnauthorizedException.class)
//    public Result unauthorizedException(Exception e){
//        return  Result.error("无权限");
//    }
//
//    @ResponseBody
//    @ExceptionHandler(AuthorizationException.class)
//    public Result authorizationException(Exception e){
//        return  Result.error("权限认证失败");
//    }
//
//}
