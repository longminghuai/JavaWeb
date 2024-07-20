package jsu.lmh.project1.controller;

import jsu.lmh.project1.entity.*;
import jsu.lmh.project1.service.ScoreSegmentService;
import jsu.lmh.project1.service.VolunteerService;
import net.sf.saxon.value.IntegerRange;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VolunteerController {
    @Autowired
    VolunteerService volunteerService;

    @Autowired
    ScoreSegmentService scoreSegmentService;
    /**
     * 智能填报志愿
     * @return
     */
    @RequiresPermissions(("query_volunteer_intelligent"))
    @GetMapping("query_volunteer_intelligent")
    public Result query_volunteer_intelligent( @RequestParam(defaultValue = "1") Integer page,
                                               @RequestParam(defaultValue = "10") Integer pagesize,
                                               String level1_name, String level2_name, String level3_name, String spname,
                                               String college_name, String province, String city, String type_name,
                                               String nature_name, Integer dual_class_name, Integer f985,Integer f211, Integer rank,
                                               String fk,Integer type){
        System.out.println("page: " + page);
        System.out.println("pagesize: " + pagesize);
        System.out.println("level1_name: " + level1_name);
        System.out.println("level2_name: " + level2_name);
        System.out.println("level3_name: " + level3_name);
        System.out.println("spname: " + spname);
        System.out.println("college_name: " + college_name);
        System.out.println("province: " + province);
        System.out.println("city: " + city);
        System.out.println("type_name: " + type_name);
        System.out.println("nature_name: " + nature_name);
        System.out.println("dual_class_name: " + dual_class_name);
        System.out.println("f985: " + f985);
        System.out.println("f211: " + f211);
        System.out.println("rank: " + rank);
        System.out.println("fk: " + fk);
        System.out.println("type: " + type);
        PageBean pageBean=volunteerService.query_volunteer_intelligent(page,
                pagesize,
                level1_name, level2_name, level3_name, spname,
                college_name, province, city, type_name,
                nature_name, dual_class_name, f985,f211, rank,
                fk,type);
        return Result.success(pageBean);
    }

    @GetMapping("getquery_volunteer_intelligent")
    public Result query_volunteer_intelligent(String spname, String college_name, String province,Integer type,Integer rank,String fk){

        System.out.println("spname: " + spname);
        System.out.println("college_name: " + college_name);
        System.out.println("province: " + province);
        System.out.println("type: " + type);
        List<college_major> list=volunteerService.getquery_volunteer_intelligent(
                 spname,
                college_name, province, rank, fk,type);
        return Result.success(list);
    }

    @GetMapping("getrank")
    public Result getrank(int score,String fk){
        System.out.println(fk+" "+score);
        return Result.success(scoreSegmentService.getrank(score,fk));
    }

    @PostMapping("/volunteer")
    public Result insertvolunteer(@RequestBody Volunteer volunteer){
        System.out.println(volunteer.getCollegeid()+" "+volunteer.getMajorid()+" "+volunteer.getUserid());
        System.out.println("slllls");
        System.out.println(volunteer.getUser_volunteerid());
        System.out.println(volunteer.getCollegename());
        volunteerService.insertvolunteer(volunteer,volunteer.getUserid());
        return Result.success();
    }

    /**
     * 我的志愿表
     * @return
     */
    @GetMapping("/myvolunteer")
    public Result querymyvolunteer(@RequestParam("id") Long id){
        return Result.success(volunteerService.querymyvolunteer(id));
    }

    @DeleteMapping("/volunteer")
    public Result deletevolunteer(@RequestParam("id") Long id, @RequestParam("user_volunteerid") Long user_volunteerid){
        System.out.println("进入删除志愿");
        volunteerService.deletevolunteer(id,user_volunteerid);
        return Result.success();
    }
    @PutMapping("updatevolunteer")
    public Result updatevolunteer(){
        return null;
    }

}
