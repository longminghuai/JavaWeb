package jsu.lmh.project1.controller;

import com.example.springboot_test2.pojo.OurClass;
import com.example.springboot_test2.pojo.Result;
import com.example.springboot_test2.service.ClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//    private ClassService clsService;
//    @RequestMapping(value = "/classe
//    s",method = RequestMethod.GET)//指定请求方式为get
@Slf4j
@RestController
//@CrossOrigin
public class classController {
    @Autowired
    private ClassService classService;

    @GetMapping("/class")
    public Result listallclass() {
        List<OurClass> clsList = classService.listallclass();
        // 添加日志记录
        log.info("Classes returned to client: {}", clsList);
        return Result.success(clsList);
    }

    /*
    根据id删除班级
     */
    @DeleteMapping("/class/{id}")
    public Result Deletebyid(@PathVariable String id) {
        System.out.println("-------------" + id);
        classService.deletebyid(id);
        return Result.success();
    }

    /*
    新增班级,请求数据为jsion类型
     */
    @PostMapping("/class")
    public Result insertclass(@RequestBody OurClass ourClass) {
//        System.out.println("-----------"+ourClass);
        int num = classService.insertclass(ourClass);
        return Result.success();
    }

    /*
    修改班级信息
     */
    @PutMapping("/class/{id}")
    public Result updateClass(@PathVariable String id, @RequestBody OurClass ourClass) {
        // 检查班级是否存在，如果不存在则返回错误
        // 如果存在，更新班级信息
        System.out.println(id);
        System.out.println(ourClass); // 打印出接收到的数据
        boolean isUpdated = classService.updateClass(id, ourClass);
        if (isUpdated) {
            return Result.success();
        } else {
            return Result.error("更新失败或班级不存在");
        }
    }

    @GetMapping("/getallclass")
    public Result getAllclass() {
        List<String> list = classService.getAllclass();
        return Result.success(list);
    }


}

