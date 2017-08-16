package com.controller;

import com.dao.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dzkan on 2016/3/18.
 */
@Controller

public class UploadController {

    @Autowired
    BlogService blogService;

    // 查看所有博文
    @ResponseBody
    @RequestMapping(value = "/controller", method = RequestMethod.POST)
    public Map  showBlogs(@RequestParam(required = false,value = "upfile")  MultipartFile file, HttpServletRequest request, Model model)throws IOException {
        System.out.println("开始");
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
//        String fileName = new Date().getTime()+".jpg";
        System.out.println(path);
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,String> map=new HashMap<String,String>();
        map.put("original",fileName);
        map.put("size", String.valueOf(file.getSize()));
        map.put("state","SUCCESS");
        map.put("type","docx");
        map.put("url","https://www.hao123.com/");

        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/save")
    public String  saveBlogs(@RequestBody Map<String,String> map, HttpSession session){
        //Map map=new HashMap<>();
        String a=(String) session.getAttribute("a");
        session.setAttribute("a","a");
        blogService.saveBlog(map);
        return  "0";
    }
    @ResponseBody
    @RequestMapping(value = "/seldecffft")
    public Map selectBlogs(@RequestBody Map<String,Object> map, HttpSession session){
        String a=(String) session.getAttribute("a");
        session.setAttribute("a","a");
        map=blogService.selectBlog(map);
        return  map;
    }
    @ResponseBody
    @RequestMapping(value = "/deldffdfdffffffect")
    public Map select( HttpSession session){
        String a=(String) session.getAttribute("a");
        session.setAttribute("a","a");
        Map map=new HashMap<String,String>();
        map.put("id","111111")   ;
        map.put("name","1");
        map.put("email","1");
        return  map;
    }
}
