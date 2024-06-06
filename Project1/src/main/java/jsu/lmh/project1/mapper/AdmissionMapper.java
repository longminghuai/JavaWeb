package jsu.lmh.project1.mapper;


import jsu.lmh.project1.entity.Admission;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdmissionMapper {

    Long selectCount(String name, String classId, String startScore, String endScore, String college_name, String college_batch);
    List<Admission_detailed> selectallAdmission(Integer startIndex, Integer pagesize, String name, String classId, String startScore, String endScore, String college_name, String college_batch);

    List<Briefly> select_briefly();

    List<ClassBriefly> select_classbriefly();

    @Update("update admission set college_id=#{college_id} where exam_number=#{id}")
    boolean update_admission(int college_id,String id);

    @Insert("insert into admission values(#{admission.exam_number},#{admission.college_id})")
    int insertAdmission(@Param("admission") Admission admission);

    @Select("Select * from students_not_admitted")
    @Results({
            @Result(property = "exam_number", column = "考号"),
            @Result(property = "name", column = "姓名"),
            @Result(property = "class_name", column = "班级")
    })
    List<Not_admission> selectnotadmission();

    @Delete("delete from admission where exam_number=#{id}")
    void deleteAdmission(String id);
}
