package com.bestyiworld.backend.dao.impl;

import com.bestyiworld.backend.dao.ArticleDao;
import com.bestyiworld.backend.dao.LoginDao;
import com.bestyiworld.entity.Article;
import com.bestyiworld.entity.User;
import com.bestyiworld.utils.DateUtil;
import com.bestyiworld.utils.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/11/8.
 */
@Repository
public class ArticleDaoImpl implements ArticleDao {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private JdbcTemplate jdbcTemplate;

    @Override
    public String add(Article article) {
        int result=jdbcTemplate.update("insert into article(id, user_id,createtime,updatetime,title,type,author,text,status,tag,category) values(?,?,?,?,?,?,?,?,?,?,?)",
                article.getId(),
                article.getUserId(),
                DateUtil.formatNormalDateString(new Date()),
                DateUtil.formatNormalDateString(new Date()),
                article.getTitle(),
                article.getType(),
                article.getAuthor(),
                article.getText(),
                article.getStatus(),
                article.getTag(),
                article.getCategory());
        return FormatUtil.Object2String(result);
    }

    @Override
    public String update(Article article) {
        int result=jdbcTemplate.update("UPDATE article set updatetime=?,title=?,type=?,text=?,status=?,category=?,tag=? WHERE id=?",
                DateUtil.formatNormalDateString(new Date()),
                article.getTitle(),
                article.getType(),
                article.getText(),
                article.getStatus(),
                article.getCategory(),
                article.getTag(),
                article.getId());
        return FormatUtil.Object2String(result);
    }

    @Override
    public String delete(String articleIds) {
        String [] articleIdArray=articleIds.split(",");
        int result=0;
        for (int i = 0; i <articleIdArray.length ; i++) {
            result += jdbcTemplate.update("UPDATE article SET status=3 where id=?",articleIdArray[i]);
        }
        return result==articleIdArray.length?"fullDelete":"NotfullDelete";
    }
}
