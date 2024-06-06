package jsu.lmh.project1.service.impl;

import com.example.springboot_test2.mapper.ClassMapper;
import com.example.springboot_test2.pojo.OurClass;
import com.example.springboot_test2.service.ClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassMapper classMapper;
    @Override
    public List<OurClass> listallclass() {
        List<OurClass> classes = classMapper.listallclass();
        // 添加日志记录
        log.info("Classes retrieved from database: {}", classes);
        return classes;
    }

    @Override
    public void deletebyid(String id) {
        classMapper.deletebyid(id);
    }

    @Override
    public int insertclass(OurClass ourClass) {
        //业务层补全属性
        classMapper.insertclass(ourClass);
        return 0;
    }

    @Override
    public boolean updateClass(String id, OurClass ourClass) {
        return classMapper.updateClass(id,ourClass);
    }

    @Override
    public List<String> getAllclass() {
        return classMapper.getAllclass();
    }
}
