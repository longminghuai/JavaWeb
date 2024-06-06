package jsu.lmh.project1.mapper;


import jsu.lmh.project1.entity.College;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CollegeMapper{
//    @Select("select count(*) from college where college_name like #{college_name} and college_address like #{college_address}")
//    Long selectCount(String college_name, String college_address);


    //跳过前20行，然后返回第21到第30行的数据。

    List<College> selectPage(Integer startindex, Integer pagesize, Integer college_id, String college_name, String college_address, String college_batch);


    long selectcount(Integer college_id, String college_name, String college_address, String college_batch);

    void Deletebyid(Integer id);

    int insertCollege(College college);

    @Update("update college set college_id=#{id},college_name=#{college.college_name},college_address=#{college.college_address},batch=#{college.college_batch} where college_id=#{id}")
    boolean update_college(@Param("college") College college, Integer id);

    @Select("select college_id,college_name from college")
    @Results({
            @Result(property = "college_id", column = "college_id"),
            @Result(property = "college_name", column = "college_name")
    })
    List<collegebreifly> selectallCollege();
}
