package com.bestyiworld.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.bestyiworld.backend.service.ArticleService;
import com.bestyiworld.entity.Article;
import com.bestyiworld.utils.DateUtil;
import com.bestyiworld.utils.ServletUtil;
import com.bestyiworld.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class ArticleController {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ArticleService articleService;

    @RequestMapping(value = "/Article/addOrUpdate",method = RequestMethod.POST)
    @ResponseBody
    public String addOrUpdate(HttpServletRequest request, HttpServletResponse response){
        JSONObject result=new JSONObject();
        String user_id="test";      //redis中取
        String author="test";
        String articleId=request.getParameter("id");
        String operateType=request.getParameter("operateType");
        String title=request.getParameter("title");
        String type=request.getParameter("type");
        String text=request.getParameter("text");
        String category=request.getParameter("category");
        String tag=request.getParameter("tag");
        Article article=new Article("",user_id,"","",title,type,author,text,"",category,tag);
        switch (operateType){
            case "1":       //添加
                article.setId(article.getUserId()+DateUtil.formatNormalDateStringId(new Date()));
                article.setType("1");
                result.put("result",articleService.add(article));
            case "2":       //保存
                if(StringUtil.isNull(articleId)){   //未添加就保存
                    article.setId(article.getUserId()+DateUtil.formatNormalDateStringId(new Date()));
                    article.setType("2");
                    result.put("result",articleService.add(article));
                }else{
                    article.setId(articleId);
                    article.setType("2");
                    result.put("result",articleService.update(article));
                }
            case "3":       //修改
                article.setId(articleId);
                article.setType("1");
                result.put("result",articleService.update(article));
        }
        ServletUtil.createSuccessResponse(200, result, response);
        //return "main";
        return "";
    }

    @RequestMapping(value = "/Article/delete/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(HttpServletRequest request, HttpServletResponse response,@PathVariable("ids") String ids){
        JSONObject result=new JSONObject();
        result.put("result",articleService.delete(ids));
        ServletUtil.createSuccessResponse(200, result, response);
        return "";
    }

}
