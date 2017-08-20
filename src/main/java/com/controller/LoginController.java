package com.controller;


import com.dao.FunDao;
import com.util.ParentController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017-08-16.
 */
@Controller
@RequestMapping("/login")
public class LoginController extends ParentController {
    @Resource
    FunDao funDao;

    @RequestMapping(method =RequestMethod.GET)
    public ModelAndView   get()
    {
       return new ModelAndView("/index.html");

    }

}
