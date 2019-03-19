package com.bestyiworld.backend.service.impl;

import com.bestyiworld.backend.dao.ArticleDao;
import com.bestyiworld.backend.service.ArticleService;
import com.bestyiworld.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/11/8.
 */
@Service
public class ArticleServiceImpl implements ArticleService {


    @Autowired
    private ArticleDao articleDao;

    @Override
    public String add(Article article) {
        return articleDao.add(article);
    }

    @Override
    public String update(Article article) {
        return articleDao.update(article);
    }

    @Override
    public String delete(String articleIds) {
        return articleDao.delete(articleIds);
    }
}
