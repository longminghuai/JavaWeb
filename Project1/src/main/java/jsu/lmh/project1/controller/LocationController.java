package jsu.lmh.project1.controller;

import jsu.lmh.project1.entity.Result;
import jsu.lmh.project1.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationController {
    @Autowired
    LocationService locationService;
    @GetMapping("getcity")
    public Result getcity(String province){
        List<String> list=locationService.getcity(province);
        return Result.success(list);
    }
}
