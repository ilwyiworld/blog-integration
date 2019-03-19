package com.bestyiworld.backend.dao;

import com.bestyiworld.entity.Article;

/**
 * Created by Administrator on 2017/11/8.
 */
public interface ArticleDao {
    String add(Article article);
    String update(Article article);
    String delete(String articleIds);
}
