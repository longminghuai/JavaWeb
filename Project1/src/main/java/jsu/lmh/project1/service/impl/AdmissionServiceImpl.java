package jsu.lmh.project1.service.impl;

import com.example.springboot_test2.mapper.AdmissionMapper;
import com.example.springboot_test2.pojo.*;
import com.example.springboot_test2.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdmissionServiceImpl implements AdmissionService {
    @Autowired
    private AdmissionMapper admissionMapper;

    @Override
    public PageBean selectallAdmission(Integer page, Integer pagesize, String name, String classId, String startScore, String endScore, String college_name, String college_batch) {
        int startindex=(page-1)*pagesize;
        List<Admission_detailed> admissions=admissionMapper.selectallAdmission(startindex, pagesize, name, classId, startScore, endScore,college_name,college_batch);
        //创建pageBean对象
        for(Admission_detailed admission :admissions){
            System.out.println(admission);
        }
//        System.out.println(colleges.size());
        long total =admissionMapper.selectCount(name, classId, startScore, endScore,college_name,college_batch);
        PageBean pageBean=new PageBean();
        pageBean.setTotal(total);
        pageBean.setRows(admissions);
        return pageBean;
    }

    @Override
    public List<Briefly> select_briefly() {
        return admissionMapper.select_briefly();
    }

    @Override
    public List<ClassBriefly> select_classbriefly() {
        return admissionMapper.select_classbriefly() ;
    }

    @Override
    public boolean update_admission(int college_id,String id) {
        return admissionMapper.update_admission(college_id,id);
    }

    @Override
    public int insertAdmission(Admission admission) {
        return admissionMapper.insertAdmission(admission);
    }

    @Override
    public List<Not_admission> selectnotadmission() {
        return admissionMapper.selectnotadmission();
    }

    @Override
    public void deleteAdmission(String id) {
        admissionMapper.deleteAdmission(id);
    }
}
