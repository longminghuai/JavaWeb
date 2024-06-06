package jsu.lmh.project1.mapper;

import com.example.springboot_test2.pojo.Login;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    Login Login(Login login);
//    Login
}
