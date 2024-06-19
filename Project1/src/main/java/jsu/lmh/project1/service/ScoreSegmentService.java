package jsu.lmh.project1.service;

import jsu.lmh.project1.entity.PageBean;
import jsu.lmh.project1.entity.ScoreSegment;
import jsu.lmh.project1.mapper.ScoreSegmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreSegmentService {
    @Autowired
    ScoreSegmentMapper scoreSegmentMapper;
    public PageBean queryseg(Integer page, Integer pagesize,Integer year, String type, Integer score) {
        int startindex=(page-1)*pagesize;
        List<ScoreSegment> list;
        Long total;
        if(type=="物理"){
            list=scoreSegmentMapper.queryseg_wl(startindex, pagesize, score);
            total=scoreSegmentMapper.querycount_wl(score);
        }
        else{
            list=scoreSegmentMapper.queryseg_ls(startindex, pagesize, score);
            total=scoreSegmentMapper.querycount_ls(score);
        }
        PageBean pageBean=new PageBean();
        System.out.println(total);
        System.out.println(list);
        pageBean.setTotal(total);
        pageBean.setRows(list);
        return pageBean;
    }
}
