package jsu.lmh.project1.controller;

import jsu.lmh.project1.entity.Result;
import jsu.lmh.project1.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VolunteerController {
    @Autowired
    VolunteerService volunteerService;
    @GetMapping("query_volunteer_intelligent")
    public Result query_volunteer_intelligent(){
        return null;
    }

}
