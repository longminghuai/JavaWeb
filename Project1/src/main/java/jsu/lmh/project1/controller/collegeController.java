package jsu.lmh.project1.controller;

import com.example.springboot_test2.pojo.*;
import com.example.springboot_test2.service.CollegeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class collegeController {
    @Autowired
    private CollegeService collegeService;
    @GetMapping("/college")
    public Result selectPage(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer pagesize,
                             Integer college_id,
                             String college_name,
                             String college_address,
                             String college_batch
    )
    {
        System.out.println(page);
        System.out.println(pagesize);
        PageBean pageBean=collegeService.selectPage(page,pagesize,college_id,college_name,college_address,college_batch);
        return Result.success(pageBean);
    }

    @DeleteMapping("/college/{id}")
    public Result Deletebyid(@PathVariable Integer id){
        System.out.println("-------------"+id);
        collegeService.Deletebyid(id);
        return Result.success();
    }

    /*
    新增班级,请求数据为jsion类型
     */
    @PostMapping("/college")
    public Result insertCollege(@RequestBody College college){
        int num=collegeService.insertCollege(college);
        if(num==1){
            return Result.success();
        }
        else{
            return Result.error("主键值重复");
        }
    }
    /*
    修改学校信息
     */
    @PutMapping("/college/{id}")
    public Result update_college(@RequestBody College college,@PathVariable Integer id){
        boolean flag=collegeService.update_college(college,id);
        if(flag){
            return Result.success();
        }
        else{
            return Result.error("主键值重复");
        }
    }
    @GetMapping("/college_b")
    public Result selectallCollege(){
        List<collegebreifly> collegebreiflyList =collegeService.selectallCollege();
        return Result.success(collegebreiflyList);
    }
}
