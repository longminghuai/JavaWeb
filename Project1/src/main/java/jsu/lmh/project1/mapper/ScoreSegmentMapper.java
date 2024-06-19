package jsu.lmh.project1.mapper;

import jsu.lmh.project1.entity.ScoreSegment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScoreSegmentMapper {

    List<ScoreSegment> queryseg_wl(int startindex, Integer pagesize,Integer score);
    List<ScoreSegment> queryseg_ls(int startindex, Integer pagesize,Integer score);
    Long querycount_wl(Integer score);
    Long querycount_ls(Integer score);
}
