package jsu.lmh.project1.service.impl;

import com.example.springboot_test2.mapper.CollegeMapper;
import com.example.springboot_test2.pojo.College;
import com.example.springboot_test2.pojo.PageBean;
import com.example.springboot_test2.pojo.collegebreifly;
import com.example.springboot_test2.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {
    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public PageBean selectPage(Integer page, Integer pagesize, Integer college_id, String college_name, String college_address, String college_batch) {

        //1.设置分页查询的参数(页码，页长)
//        PageHelper.startPage(page,pagesize);
        //2.查询所有的数据
//        List<College> colleges=collegeMapper.selectPage();
//        return collegeMapper.selectPage(page,pagesize);
//        Long total=collegeMapper.selectCount(college_name,college_address);
        //计算起始索引
        int startindex=(page-1)*pagesize;
        List<College> colleges=collegeMapper.selectPage(startindex,pagesize,college_id,college_name,college_address,college_batch);
        //创建pageBean对象
        for(College college:colleges){
            System.out.println(college);
        }
//        System.out.println(colleges.size());
        long total =collegeMapper.selectcount(college_id,college_name, college_address,college_batch);
        PageBean pageBean=new PageBean();
        pageBean.setTotal(total);
        pageBean.setRows(colleges);
        return pageBean;
    }

    @Override
    public void Deletebyid(Integer id) {
        collegeMapper.Deletebyid(id);
    }

    @Override
    public int insertCollege(College college) {
        return collegeMapper.insertCollege(college);
    }

    @Override
    public boolean update_college(College college, Integer id) {
        return collegeMapper.update_college(college,id);
    }

    @Override
    public List<collegebreifly> selectallCollege() {
        return collegeMapper.selectallCollege();
    }

//    public PageBean selectPage(){
////        rey=turn
//    }
}
