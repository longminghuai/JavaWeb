package jsu.lmh.project1.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface College_cityMapper {
    @Insert("insert into college_city(college_id,city_id) values(#{id},#{cityId})")
    void insertob(Long id, Long cityId);
}
