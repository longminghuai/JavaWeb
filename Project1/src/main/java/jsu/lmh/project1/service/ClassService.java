package jsu.lmh.project1.service;

import com.example.springboot_test2.pojo.OurClass;

import java.util.List;

public interface ClassService {
    List<OurClass> listallclass();

    void deletebyid(String id);

    int insertclass(OurClass ourClass);

    boolean updateClass(String id, OurClass ourClass);

    List<String> getAllclass();
}
