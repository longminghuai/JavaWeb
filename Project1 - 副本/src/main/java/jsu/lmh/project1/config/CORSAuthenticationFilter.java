//package jsu.lmh.project1.config;
//
//import com.alibaba.fastjson.JSON;
//import jsu.lmh.project1.entity.Result;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
//
//@Slf4j
//public class CORSAuthenticationFilter extends FormAuthenticationFilter {
//
//    @Override
//    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        //Always return true if the request's method is OPTIONSif (request instanceof HttpServletRequest) {
//        if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
//            return true;
//        }
//        return super.isAccessAllowed(request, response, mappedValue);
//    }
//
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        HttpServletResponse res = (HttpServletResponse) response;
//        res.setHeader("Access-Control-Allow-Origin", "*");
//        res.setStatus(HttpServletResponse.SC_OK);
//        res.setCharacterEncoding("utf-8");
//        PrintWriter writer = res.getWriter();
//        writer.write(JSON.toJSONString(Result.error("错误")));
//        writer.close();
//        return false;
//    }
//}
