package jsu.lmh.project1.controller;

import jsu.lmh.project1.entity.PageBean;
import jsu.lmh.project1.entity.Result;
import jsu.lmh.project1.service.ScoreSegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreSegmentController {
    @Autowired
    public ScoreSegmentService scoreSegmentService;
    @GetMapping("oneseg")
    public Result queryseg(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer pagesize,@RequestParam(defaultValue = "2023") Integer year,
                             @RequestParam(defaultValue = "物理") String type,Integer score){
        System.out.println(page+" "+pagesize+" "+year+" "+type+" "+score);
        PageBean pageBean=scoreSegmentService.queryseg(page,pagesize,year,type,score);
        if(pageBean!=null){
            System.out.println(pageBean.rows);
            return Result.success(pageBean);
        }
        else{
            return Result.error("查询错误");
        }
    }
}
