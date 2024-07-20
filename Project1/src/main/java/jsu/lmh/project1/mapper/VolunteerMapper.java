package jsu.lmh.project1.mapper;

import jsu.lmh.project1.entity.Volunteer;
import jsu.lmh.project1.entity.college_major;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface VolunteerMapper {
    Long selectcount(String level1Name, String level2Name, String level3Name, String spname, String collegeName, String province, String city, String typeName, String natureName, Integer dualClassName, Integer f985, Integer f211, Integer minn,Integer maxn, String fk);

    List<college_major> query_volunteer_intelligent(Integer startindex, Integer pagesize, String level1Name, String level2Name, String level3Name, String spname, String collegeName, String province, String city, String typeName, String natureName, Integer dualClassName, Integer f985, Integer f211, Integer minn,Integer maxn, String fk);

    @Select("select * from volunteer where userid=#{id}")
    List<Volunteer> querymyvolunteer(Long id);

    @Delete("DELETE FROM volunteer WHERE userid=#{id} and user_volunteerid=#{user_volunteerid}")
    void deletevolunteer(Long id, Long user_volunteerid);

    @Insert("insert into volunteer(userid,collegeid,collegename,majorid,majorname,num,`rank`,user_volunteerid) values(#{volunteer.userid},#{volunteer.collegeid},#{volunteer.collegename},#{volunteer.majorid},#{volunteer.majorname},#{volunteer.num},#{volunteer.rank},#{volunteer.user_volunteerid})")
    void insertvolunteer(Volunteer volunteer, Long userid);

    List<college_major> getquery_volunteer_intelligent(String spname, String college_name, String province, int minn, int maxn, String fk);
}
