package com.bestyiworld.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.bestyiworld.backend.service.LoginService;
import com.bestyiworld.utils.ServletUtil;
import com.bestyiworld.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private LoginService loginService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
        JSONObject result=new JSONObject();
        String loginName=request.getParameter("loginName");
        String password=request.getParameter("password");
        int validate;
        if(!StringUtil.isNull(loginName) && !StringUtil.isNull(password)){
            validate=loginService.validate(loginName,password);
            if(validate==1){
                request.getSession().setAttribute("user",loginName);
                result.put("result","1");
            }else{
                result.put("result","0");
            }
        }else{
            result.put("result","0");
        }
        ServletUtil.createSuccessResponse(200, result, response);
        //return "main";
        return new ModelAndView("main");
    }
}
