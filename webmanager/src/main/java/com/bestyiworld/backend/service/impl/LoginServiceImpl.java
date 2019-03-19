package com.bestyiworld.backend.service.impl;

import com.bestyiworld.backend.dao.LoginDao;
import com.bestyiworld.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/11/8.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Override
    public int validate(String loginName, String password) {
        return loginDao.validate(loginName,password);
    }
}
