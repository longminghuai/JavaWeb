//package jsu.lmh.project1.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jsu.lmh.project1.entity.Result;
//import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
//
//
//public class CORSFilter extends FormAuthenticationFilter {
//    @Override
//    protected boolean isAccessAllowed(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, Object mappedValue) {
//        if(((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")){
//            //如果拦截的是预检请求，让shiro放行，不做任何处理
//            return true;
//        }
//        return super.isAccessAllowed(request,response,mappedValue);
//    }
//
//    @Override
//    protected boolean onAccessDenied(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response) throws Exception {
//        HttpServletResponse resp=(HttpServletResponse) response;
//        //返回码为200才能到达vue的.then语句
//        resp.setStatus(HttpServletResponse.SC_OK);
//        resp.setContentType("application/json;charset=utf8");
//        resp.setHeader("Access-Control-Allow-Origin","http://localhost:9001");
//        resp.setHeader("Access-Control-Allow-Methods","*");
//        resp.setHeader("Access-Control-Allow-Headers","*");
//        resp.setHeader("Access-Control-Allow-Credentials","true");
//        PrintWriter writer=resp.getWriter();
//        Result result = new Result();
//        result.setCode(401);
//        result.setMsg("您没有授权");
//        ObjectMapper om=new ObjectMapper();
//        String json = om.writeValueAsString(result);
//        writer.write(json);
//        writer.flush();
//        writer.close();
//        return false;
//    }
//}
