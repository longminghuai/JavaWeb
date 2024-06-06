package jsu.lmh.project1.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {

//    List<Student> selectStudent(Integer page,Integer pagesize,String className, String name, String gender);

    void DeleteBynumber(String id);

    int insertStudent(StudentDetail student);

    long selectcount(String className, String name, String gender);

    List<StudentDetail> selectPage(int startindex, Integer pagesize, String className, String name, String gender);

    boolean update_student(@Param("student") StudentDetail student, String id);
}
