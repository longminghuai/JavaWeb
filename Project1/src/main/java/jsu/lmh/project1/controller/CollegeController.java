package jsu.lmh.project1.controller;

import jsu.lmh.project1.entity.College;
import jsu.lmh.project1.entity.PageBean;
import jsu.lmh.project1.entity.Result;
import jsu.lmh.project1.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CollegeController {
    @Autowired
    public CollegeService collegeService;
//    @RequiresRoles("vip")
    @GetMapping("college")
    public Result listcollege(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pagesize,
                              String college_name, String province, String type_name, String nature_name,
                              String level_name, Integer dual_class_name, Integer f985, Integer f211
                              ){
        System.out.println("进入后端");
        System.out.println(page);
        System.out.println(province);
        System.out.println(type_name);
        if(f985!=null&&f985==1){
            f211=1;
            dual_class_name=1;
        }
        if(f211!=null&&f211==1){
            dual_class_name=1;
        }
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

    @GetMapping("getcollege")
    public Result listcollege_android(
                              String college_name, String province, String type_name, String nature_name,
                              String level_name, Integer dual_class_name, Integer f985, Integer f211
    ){
        System.out.println("进入后端");
        System.out.println(province);
        System.out.println(type_name);
        if(f985!=null&&f985==1){
            f211=1;
            dual_class_name=1;
        }
        if(f211!=null&&f211==1){
            dual_class_name=1;
        }
        System.out.println("是否为双一流"+dual_class_name);
        System.out.println("是否为985:"+f985);
        List<College> list= collegeService.listcollege_android(college_name,province,type_name,nature_name,level_name,dual_class_name,f985,f211);
//        List<College> list=collegeService.listcollege();
        if(list!=null){
            System.out.println(list);
            return Result.success(list);
        }
        else{
            return Result.error("查询错误");
        }
    }

    /*
    新增大学,请求数据为jsion类型
     */
    @PostMapping("/college")
    public Result insertCollege(@RequestBody College college){
        if(college.getF985()==1){
            college.setF211(1);
            college.setDual_class_name(1);
        }
        if(college.getF211()==1){
            college.setDual_class_name(1);
        }
        int num=collegeService.insertCollege(college);
        if(num==1){
            return Result.success();
        }
        else{
            return Result.error("主键值重复");
        }
    }

    @DeleteMapping("college")
    public Result detelecollege(@RequestParam("id") Long original_college_id){
        System.out.println(original_college_id);
        collegeService.deletebyid(original_college_id);
        return Result.success();
    }
    @PutMapping("college")
    public Result updatecollege(@RequestBody College college){
        if(college.getF985()==1){
            college.setF211(1);
            college.setDual_class_name(1);
        }
        if(college.getF211()==1){
            college.setDual_class_name(1);
        }
        collegeService.updatecollege(college,college.getId());
        return Result.success();
    }
}
