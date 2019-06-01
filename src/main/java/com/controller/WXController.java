package com.controller;


import com.dao.NewsDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.model.Pagination;
import com.util.ParentController;
import com.util.ValidatePermission;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 666 on 2018-04-21
 */
@Controller
public class WXController extends ParentController {
    @Resource
    NewsDao newsDao;

    /**
     * 微信消息接收和token验证
     * @param model
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/WX")
    @ResponseBody
    public void hello(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        PrintWriter print;
        if (isGet) {
            // 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 随机字符串
            String echostr = request.getParameter("echostr");
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
           // if (signature != null && CheckoutUtil.checkSignature(signature, timestamp, nonce)) {
                try {
                    print = response.getWriter();
                    print.write(echostr);
                    print.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    /**
     * 创建菜单
     * */
    @RequestMapping("/createMenu")
    @ResponseBody
    public Map  createMenu(){
//调用UTI执行创建菜单的动作
        String a="{\n" +
                "    \"button\": [\n" +
                "        {\n" +
                "            \"name\": \"博客\", \n" +
                "            \"type\": \"view\", \n" +
                "            \"url\": \"http://www.cuiyongzhi.com\"\n" +
                "        }, \n" +
                "        {\n" +
                "            \"name\": \"菜单\", \n" +
                "            \"sub_button\": [\n" +
                "                {\n" +
                "                    \"name\": \"博客\", \n" +
                "                    \"type\": \"view\", \n" +
                "                    \"url\": \"http://www.cuiyongzhi.com\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }, \n" +
                "        {\n" +
                "            \"key\": \"text\", \n" +
                "            \"name\": \"回复图文\", \n" +
                "            \"type\": \"click\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        Map<String, String> map = new  Gson().fromJson(a, HashMap.class);
        return  map;
    }

}
