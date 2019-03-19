package com.bestyiworld.backend.dao.impl;

import com.bestyiworld.backend.dao.LoginDao;
import com.bestyiworld.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8.
 */
@Repository
public class LoginDaoImpl implements LoginDao{

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private JdbcTemplate jdbcTemplate;

    @Override
    public int validate(String loginName, String password) {
        List<User> list = jdbcTemplate.query("select 1 from user_info where login_name = ? AND password = ?", new Object[]{loginName,password}, new BeanPropertyRowMapper(User.class));
        return list.size();
    }
}
