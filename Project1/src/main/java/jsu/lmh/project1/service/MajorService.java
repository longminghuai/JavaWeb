package jsu.lmh.project1.service;

import jsu.lmh.project1.entity.Major;
import jsu.lmh.project1.entity.PageBean;
import jsu.lmh.project1.mapper.MajorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorService {
    @Autowired
    MajorMapper majorMapper;
    public PageBean query_all(Integer page, Integer pagesize, String level1Name, String level2Name, String spname) {
        int startindex=(page-1)*pagesize;
        System.out.println(startindex);
        List<Major> majors=majorMapper.query_all(startindex,pagesize,level1Name,level2Name,spname);
        long total =majorMapper.selectcount(level1Name,level2Name,spname);
        PageBean pageBean=new PageBean();
        System.out.println(total);
        System.out.println(majors);
        pageBean.setTotal(total);
        pageBean.setRows(majors);
        return pageBean;
    }

    public void deletemajor(Integer id) {
        majorMapper.deletemajor(id);
    }

    public int insertMajor(Major major) {
        Long num=majorMapper.selectcount_major(major.getLevel1_name(),major.getLevel2_name(),major.getLevel3_name(),major.getSpname());
        if(num>0){
            return 0;
        }
        return majorMapper.insertMajor(major);
    }

    public boolean update_major(Major major, Integer id) {
        return majorMapper.update_major(major,id);
    }

    public List<String> getmajor_d(String level1Name, String level2Name) {
        return majorMapper.getmajor_d(level1Name,level2Name);
    }

    public List<Major> query_all_android(String level1_name, String spname) {
        return majorMapper.getmajor_android(level1_name,spname);
    }
}
