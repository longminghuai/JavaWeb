package jsu.lmh.project1.mapper;

import jsu.lmh.project1.entity.ScoreSegment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScoreSegmentMapper {

    List<ScoreSegment> queryseg_wl(int startindex, Integer pagesize,Integer value);
    List<ScoreSegment> queryseg_ls(int startindex, Integer pagesize,Integer value);
    Long querycount_wl(Integer value);
    Long querycount_ls(Integer value);
    @Select("select total from lishi where score =#{score}")
    Integer getrankls(int score);

    @Select("select total from wuli where score =#{score}")
    Integer getrankwl(int score);
}
