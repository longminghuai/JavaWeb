package jsu.lmh.project1.service;

import com.example.springboot_test2.pojo.PageBean;
import com.example.springboot_test2.pojo.StudentDetail;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {

    PageBean selectStudent(Integer page, Integer pagesize, String className, String name, String gender);

    void DeleteBynumber(String id);

    int insertStudent(StudentDetail student);

    boolean update_student(StudentDetail studentDetail, String id);
}
