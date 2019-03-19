package com.bestyiworld.backend.dao;

/**
 * Created by Administrator on 2017/11/8.
 */
public interface LoginDao {
    int validate(String loginName,String password);
}
