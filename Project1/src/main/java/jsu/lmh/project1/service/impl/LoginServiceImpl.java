package jsu.lmh.project1.service.impl;

import com.example.springboot_test2.mapper.LoginMapper;
import com.example.springboot_test2.pojo.Login;
import com.example.springboot_test2.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Override
    public Login Login(Login login) {
        return loginMapper.Login(login);
    }
}
