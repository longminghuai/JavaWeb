package jsu.lmh.project1.mapper;

import jsu.lmh.project1.entity.College;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollegeMapper {
    public List<College> listcollege(Integer startindex,
                                     Integer pagesize,
                                     String college_name,String province,String type_name,String nature_name,
                                     String level_name,Integer dual_class_name,Integer f985,Integer f211);

    long selectcount(String college_name,String province,String type_name,String nature_name,
                     String level_name,Integer dual_class_name,Integer f985,Integer f211);
}
