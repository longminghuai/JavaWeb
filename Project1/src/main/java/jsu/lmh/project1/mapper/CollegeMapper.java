package jsu.lmh.project1.mapper;

import jsu.lmh.project1.entity.College;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CollegeMapper {
    public List<College> listcollege(Integer startindex,
                                     Integer pagesize,
                                     String college_name,String province,String type_name,String nature_name,
                                     String level_name,Integer dual_class_name,Integer f985,Integer f211);

    long selectcount(String college_name,String province,String type_name,String nature_name,
                     String level_name,Integer dual_class_name,Integer f985,Integer f211);


    @Insert("INSERT INTO college (name, dual_class_name, f985, f211, type_name, nature_name, level_name, logo, address, email, site, phone, content) VALUES (#{name}, #{dual_class_name}, #{f985}, #{f211}, #{type_name}, #{nature_name}, #{level_name}, #{logo}, #{address}, #{email}, #{site}, #{phone}, #{content})")
    int insertCollege(College college);

    @Select("select distinct * from college where name=#{name}")
    College findcollege(String name);

    @Update("update college set name=#{college.name},f211=#{college.f211},f985=#{college.f985},level_name=#{college.level_name},nature_name=#{college.nature_name},type_name=#{college.type_name},dual_class_name=#{college.dual_class_name},logo=#{college.logo},address=#{college.address},phone=#{college.phone},site=#{college.site},email=#{college.email},content=#{college.content} where id=#{originalCollegeId}")
    void updatecollege(College college, Long originalCollegeId);

    @Update("update college_city set city_id = (select city.id from city where cityname=#{city}) where college_city.college_id=#{originalCollegeId}")
    void setCollege_city(Long originalCollegeId, String city);

    @Delete("delete from college where id=#{originalCollegeId}")
    void deletebyid(Long originalCollegeId);

    @Delete("delete from college_city where college_id=#{originalCollegeId}")
    void deletelx(Long originalCollegeId);

    List<College> listcollege_android(String college_name,String province,String type_name,String nature_name,
                                      String level_name,Integer dual_class_name,Integer f985,Integer f211);
}
