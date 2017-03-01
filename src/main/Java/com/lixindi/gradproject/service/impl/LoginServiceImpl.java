package com.lixindi.gradproject.service.impl;

import com.lixindi.gradproject.dao.AdminMapper;
import com.lixindi.gradproject.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lixindi on 2017/3/1.
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AdminMapper adminMapper;

    public String queryPassword(String username) {
        return adminMapper.selectByUsername(username);
    }
}
