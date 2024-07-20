package jsu.lmh.project1.service;

import jsu.lmh.project1.mapper.LocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    LocationMapper locationMapper;
    public List<String> getcity(String province) {
        return locationMapper.getcity(province);
    }
}
