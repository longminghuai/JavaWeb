package jsu.lmh.project1.controller;

import jsu.lmh.project1.entity.Major;
import jsu.lmh.project1.entity.PageBean;
import jsu.lmh.project1.entity.Result;
import jsu.lmh.project1.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MajorController {
    @Autowired
    MajorService majorService;

    @GetMapping("major")
    public Result query_all(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer pagesize,
                            String level1_name,String level2_name,String spname


    ){
        System.out.println(page+" "+pagesize+" "+level1_name+" "+level2_name+" "+" "+spname);
        PageBean pageBean=majorService.query_all(page,pagesize,level1_name,level2_name,spname);
        if(pageBean!=null){
            return Result.success(pageBean);
        }
        else{
            return Result.error("没有查到任何数据");
        }
    }
    @GetMapping("getmajor")
    public Result query_all(
                            String level1_name,String spname
    ){
        System.out.println(level1_name+" "+spname);
        List<Major> list=majorService.query_all_android(level1_name,spname);
        if(list!=null){
            return Result.success(list);
        }
        else{
            return Result.error("没有查到任何数据");
        }
    }
    @DeleteMapping("/major")
    public Result deletemajor(@RequestParam("id") Integer id){
        System.out.println("-------------"+id);
        majorService.deletemajor(id);
        return Result.success();
    }

    /*
    新增专业,请求数据为jsion类型
     */
    @PostMapping("/major")
    public Result insertMajor(@RequestBody Major major){
        System.out.println(major);
        int num=majorService.insertMajor(major);
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
    @PutMapping("/major")
    public Result update_major(@RequestBody Major major){
        boolean flag=majorService.update_major(major, Math.toIntExact(major.getId()));
        if(flag){
            return Result.success();
        }
        else{
            return Result.error("主键值重复");
        }
    }

    /**
     * 获取专业大类
     */
    @GetMapping("major_d")
    public Result getmajor_d(String level1_name,String level2_name){
        List<String> list=majorService.getmajor_d(level1_name,level2_name);
        return Result.success(list);
    }

}
