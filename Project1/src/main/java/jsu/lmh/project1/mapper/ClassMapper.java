package jsu.lmh.project1.mapper;
import jsu.lmh.project1.entity.OurClass;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassMapper {
    @Select("SELECT * FROM Class")
    @Results({
            @Result(property = "class_name", column = "class_name"),
            @Result(property = "class_teacher", column = "class_teacher")
    })
    List<OurClass> listallclass();
    @Delete("delete  from Class where class_name=#{id}")
    void deletebyid(String id);
    @Insert("insert into Class (class_name,class_teacher)  values(#{class_name},#{class_teacher})")
    void insertclass(OurClass ourClass);
    @Update("update Class set class_name = #{ourclass.class_name}, class_teacher = #{ourclass.class_teacher} WHERE class_name = #{id}")
    boolean updateClass(String id, @Param("ourclass") OurClass ourClass);


    @Select("select class_name from Class")
    List<String> getAllclass();
}

