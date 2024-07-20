package jsu.lmh.project1.service;

import jsu.lmh.project1.controller.CollegeController;
import jsu.lmh.project1.entity.College;
import jsu.lmh.project1.entity.PageBean;
import jsu.lmh.project1.mapper.CollegeMapper;
import jsu.lmh.project1.mapper.College_cityMapper;
import jsu.lmh.project1.mapper.LocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class CollegeService {
    @Autowired
    public CollegeMapper collegeMapper;
    @Autowired
    LocationMapper locationMapper;
    @Autowired
    College_cityMapper collegeCityMapper;
    public PageBean listcollege(Integer page,
                                Integer pagesize,
                                String college_name, String province, String type_name, String nature_name,
                                String level_name, Integer dual_class_name, Integer f985, Integer f211){
        int startindex=(page-1)*pagesize;
        System.out.println(startindex);
        List<College> colleges=collegeMapper.listcollege(startindex,pagesize,college_name,province,type_name,nature_name,level_name,dual_class_name,f985,f211);
        long total =collegeMapper.selectcount(college_name,province,type_name,nature_name,level_name,dual_class_name,f985,f211);
        PageBean pageBean=new PageBean();
        System.out.println(total);
        System.out.println(colleges);
        pageBean.setTotal(total);
        pageBean.setRows(colleges);
        return pageBean;
    }

    public int insertCollege(College college) {
        College tc=collegeMapper.findcollege(college.getName());
        if(tc!=null){
            return 0;
        }
        collegeMapper.insertCollege(college);
        Long id = collegeMapper.findcollege(college.getName()).getId();
        Long city_id= locationMapper.getcityid(college.getLocation().getCity());
        collegeCityMapper.insertob(id,city_id);
        return 1;
    }

    public void updatecollege(College college, Long originalCollegeId) {
        collegeMapper.updatecollege(college, originalCollegeId);
        collegeMapper.setCollege_city(originalCollegeId,college.getLocation().getCity());
    }

    public void deletebyid(Long originalCollegeId) {
        collegeMapper.deletebyid(originalCollegeId);
        collegeMapper.deletelx(originalCollegeId);
    }

    public List<College> listcollege_android(String college_name, String province, String type_name, String nature_name,
                                        String level_name, Integer dual_class_name, Integer f985, Integer f211) {
        List<College> list=collegeMapper.listcollege_android(college_name,province,type_name,nature_name,level_name,dual_class_name,f985,f211);
        return list;
    }
}
