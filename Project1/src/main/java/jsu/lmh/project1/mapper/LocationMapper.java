package jsu.lmh.project1.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LocationMapper {
    @Select("select cityname from city,province_city,province where province.`name`=#{province} and province.id=province_city.province_id and province_city.city_id=city.id")
    List<String> getcity(String province);

    @Select("select id from city where cityname=#{city}")
    Long getcityid(String city);
}
