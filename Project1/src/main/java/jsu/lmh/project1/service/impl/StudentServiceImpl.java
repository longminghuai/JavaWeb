package jsu.lmh.project1.service.impl;

import com.example.springboot_test2.mapper.StudentMapper;
import com.example.springboot_test2.pojo.PageBean;
import com.example.springboot_test2.pojo.StudentDetail;
import com.example.springboot_test2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public PageBean selectStudent(Integer page, Integer pagesize, String className, String name, String gender) {
        int startindex=(page-1)*pagesize;
        List<StudentDetail> studentList=studentMapper.selectPage(startindex,pagesize,className,name,gender);
        //创建pageBean对象
//        System.out.println(colleges.size());
        long total =studentMapper.selectcount(className,name,gender);
        PageBean pageBean=new PageBean();
        pageBean.setTotal(total);
        pageBean.setRows(studentList);
        return pageBean;
    }

    @Override
    public void DeleteBynumber(String id) {
        studentMapper.DeleteBynumber(id);
    }

    @Override
    public int insertStudent(StudentDetail student) {
        return studentMapper.insertStudent(student);
    }

    @Override
    public boolean update_student(StudentDetail studentDetail, String id) {
        return studentMapper.update_student(studentDetail,id);
    }
}
