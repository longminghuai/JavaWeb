package jsu.lmh.project1.controller;

import com.example.springboot_test2.pojo.*;
import com.example.springboot_test2.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j//启动日志
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/student")
    public Result selectStudent(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pagesize,String class_name, String name, String gender
    ) {
        PageBean pageBean=studentService.selectStudent(page,pagesize,class_name,name,gender);
        return Result.success(pageBean);
    }


    @DeleteMapping("/student/{id}")
    public Result DeleteBynumber(@PathVariable String id) {
        studentService.DeleteBynumber(id);
        return Result.success();
    }

    @PostMapping("/student")
    public Result insertStudent(@RequestBody StudentDetail studentDetail) {
//        System.out.println("-----------"+ourClass);
        int num = studentService.insertStudent(studentDetail);
        if (num == 1) {
            return Result.success();
        } else {
            return Result.error("主键值重复");
        }
    }
    @PutMapping("/student/{id}")
    public Result update_student(@RequestBody StudentDetail studentDetail,@PathVariable String id){
        boolean flag=studentService.update_student(studentDetail,id);
        if(flag){
            return Result.success();
        }
        else{
            return Result.error("主键值重复");
        }
    }
}
