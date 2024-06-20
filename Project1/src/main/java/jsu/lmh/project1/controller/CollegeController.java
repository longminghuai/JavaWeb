package jsu.lmh.project1.controller;

import jsu.lmh.project1.entity.PageBean;
import jsu.lmh.project1.entity.Result;
import jsu.lmh.project1.service.CollegeService;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authz.annotation.RequiresRoles;
//import org.apache.shiro.subject.Subject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollegeController {
    @Autowired
    public CollegeService collegeService;

//    @RequiresRoles("vip")
    @GetMapping("college")
    @RequiresRoles("vip")
    public Result listcollege(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pagesize,
                              String college_name,String province,String type_name,String nature_name,
                              String level_name,Integer dual_class_name,Integer f985,Integer f211
                              ){
//        Subject subject = SecurityUtils.getSubject();
//        Object principal = subject.getPrincipal();
//        System.out.println(principal);
        System.out.println("进入后端");
        System.out.println(page);
        System.out.println(province);
        System.out.println("是否为双一流"+dual_class_name);
        System.out.println("是否为985:"+f985);
        System.out.println(pagesize);
        PageBean pageBean= collegeService.listcollege(page,pagesize,college_name,province,type_name,nature_name,level_name,dual_class_name,f985,f211);
//        List<College> list=collegeService.listcollege();
        if(pageBean!=null){
            System.out.println(pageBean.rows);
            return Result.success(pageBean);
        }
        else{
            return Result.error("查询错误");
        }
    }
}
