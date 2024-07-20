package jsu.lmh.project1.mapper;

import jsu.lmh.project1.entity.Major;
import jsu.lmh.project1.entity.PageBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper

public interface MajorMapper {
    List<Major> query_all(int startindex, Integer pagesize, String level1Name, String level2Name, String spname);

    long selectcount(String level1Name, String level2Name, String spname);

    @Delete("delete from major where id=#{id}")
    void deletemajor(Integer id);

    @Update({"update major set level1_name=#{major.level1_name},level2_name=#{major.level2_name},level3_name=#{major.level3_name},spname=#{major.spname},fivesalaryavg=#{major.fivesalaryavg}," +
            "degree=#{major.degree},boy_rate=#{major.boy_rate},girl_rate=#{major.girl_rate},limit_year=#{major.limit_year} where id=#{id}"})
    boolean update_major(Major major, Integer id);

    @Insert({"insert into major(level1_name,level2_name,level3_name,spname,fivesalaryavg,degree,boy_rate,girl_rate,limit_year)" +
            " values(#{level1_name},#{level2_name},#{level3_name},#{spname},#{fivesalaryavg},#{degree},#{boy_rate},#{girl_rate},#{limit_year})"})
    int insertMajor(Major major);

    @Select("select distinct  level3_name from major where level1_name=#{level1Name} and level2_name=#{level2Name} and level3_name!=''")
    List<String> getmajor_d(String level1Name, String level2Name);

    @Select("select count(*) from major where  level1_name=#{level1Name} and level2_name=#{level2Name} and level3_name=#{level3Name} and spname=#{spname}")
    Long selectcount_major(String level1Name, String level2Name, String level3Name, String spname);

    List<Major> getmajor_android(String level1_name, String spname);
}
