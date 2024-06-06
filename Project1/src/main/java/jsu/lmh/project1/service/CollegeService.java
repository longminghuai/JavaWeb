package jsu.lmh.project1.service;

import com.example.springboot_test2.pojo.College;
import com.example.springboot_test2.pojo.PageBean;
import com.example.springboot_test2.pojo.collegebreifly;
import org.springframework.stereotype.Service;

import java.util.List;

//@Slf4j
@Service
public interface CollegeService {

    PageBean selectPage(Integer page, Integer pagesize, Integer college_id, String college_name, String college_address, String college_batch) ;

    void Deletebyid(Integer id);

    int insertCollege(College college);

    boolean update_college(College college, Integer id);

    List<collegebreifly> selectallCollege();
}
