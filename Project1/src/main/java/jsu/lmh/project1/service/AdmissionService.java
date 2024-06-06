package jsu.lmh.project1.service;

import com.example.springboot_test2.pojo.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdmissionService {
    PageBean selectallAdmission(Integer page, Integer pagesize, String name, String classId, String startScore, String endScore, String college_name, String college_batch);

    List<Briefly> select_briefly();

    List<ClassBriefly> select_classbriefly();

    boolean update_admission(int college_id,String id);

    int insertAdmission(Admission admission);

    List<Not_admission> selectnotadmission();

    void deleteAdmission(String id);
}
