package com.bestyiworld.backend.service;

import com.bestyiworld.entity.Article;

/**
 * Created by Administrator on 2017/11/8.
 */
public interface ArticleService {
    String add(Article article);
    String update(Article article);
    String delete(String articleId);
}
