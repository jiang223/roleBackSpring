//package com.service;
//
//
//import com.dao.FunDao;
//import com.sun.javafx.collections.MappingChange;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.annotation.Resource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by Administrator on 2017-08-16.
// */
//@Controller
//@RequestMapping(value = "/accessDenied")
//public class ExecptionController {
//    @Resource
//    FunDao funDao;
//
//    @RequestMapping()
//    @ResponseBody
//    public Map accessDenied(){
//        Map map=new HashMap<>();
//        map.put("suncess",false);
//        map.put("message","你无权访问");
//        return map;
//    }
//
//}
