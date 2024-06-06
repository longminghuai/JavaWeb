package jsu.lmh.project1.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class AdmissionController {
    @Autowired
    private AdmissionService admissionService;
    @GetMapping("/admission")
    public Result selectallAdmission(@RequestParam(defaultValue = "1") Integer page,
                                     @RequestParam(defaultValue = "10") Integer pagesize,
                                     String name,
                                     String class_id,
                                     @RequestParam(defaultValue = "0")String start_score,
                                     @RequestParam(defaultValue = "750") String end_score,String college_name,String college_batch){
        System.out.println(college_batch);
        PageBean pageBean=admissionService.selectallAdmission(page,pagesize,name,class_id,start_score,end_score,college_name,college_batch);
        return Result.success(pageBean);
    }

    @GetMapping("/admission_not")
    public Result selectnotadmission() {
        List<Not_admission> notAdmissionList =admissionService.selectnotadmission();
        return Result.success(notAdmissionList);
    }
    @GetMapping("/admission_briefly")
    public Result select_briefly(){
        List<Briefly> brieflyList =admissionService.select_briefly();
        return Result.success(brieflyList);
    }

    @GetMapping("/admission_classbriefly")
    public Result select_classbriefly(){
        List<ClassBriefly> classBrieflies =admissionService.select_classbriefly();
        return Result.success(classBrieflies);
    }

    @PostMapping("/admission")
    public Result insertAdmission(@RequestBody Admission admission){
        int num=admissionService.insertAdmission(admission);
        if(num==1){
            return Result.success();
        }
        else{
            return Result.error("添加录取信息失败");
        }
    }
    /*
    修改录取情况
     */
    @PutMapping("/admission/{exam_number}/{college_id}")
    public Result update_admission(@PathVariable int college_id,@PathVariable String exam_number){
        System.out.println(college_id);
        System.out.println(exam_number);
        boolean flag=admissionService.update_admission(college_id,exam_number);
        if(flag){
            return Result.success();
        }
        else{
            return Result.error("主键值重复");
        }
    }

    @DeleteMapping("/admission/{id}")
    public Result deleteAdmission(@PathVariable  String id){
        System.out.println(id);
        admissionService.deleteAdmission(id);
        return Result.success();
    }
}
