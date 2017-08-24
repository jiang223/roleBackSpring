package com.controller;


import com.dao.RoleFunDao;
import com.dao.UserDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.model.Pagination;
import com.util.ParentController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-08-16.
 */
@Controller
@RequestMapping("/user")
public class UserController extends ParentController {
    @Resource
    UserDao userDao;
    @Resource
    RoleFunDao roleFunDao;
    @RequestMapping(method =RequestMethod.POST,value = "/login")
    @ResponseBody
    public Map login(HttpSession session, @RequestBody Map map)
    {
        try {
             Map userMap=userDao.login(map);
              if (userMap!=null){
                  session.setAttribute("user",userMap);
                 return resultSucess(userMap);
              }else{
                 return resultError("账号或密码错误");
              }
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }

    }
    @RequestMapping(method =RequestMethod.GET)
    @ResponseBody
    public Map viLogin(HttpSession session)
    {
        try {
            Map userMap=(Map) session.getAttribute("user");
            if (userMap!=null){
                return resultSucess(userMap);
            }else{
                return resultError("not login");
            }
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }

    }
    @RequestMapping(method =RequestMethod.GET,value = "/menu")
    @ResponseBody
    public Map getMenu(HttpSession session)
    {
        try {
            Map userMap=(Map) session.getAttribute("user");
            List<Map> menuList=roleFunDao.findMenuByRole(userMap);
            if (menuList.size()==0){
                Map map=new HashMap<>();
                map.put("id","0");
                map.put("name","无");
                map.put("url","1");
                menuList.add(map);
            }
            return resultSucess(menuList);
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }

    }
    @RequestMapping(method =RequestMethod.GET,value = "/code")
    @ResponseBody
    public Map getMethod(HttpSession session,@RequestParam Map map)
    {
        try {
            Map userMap=(Map) session.getAttribute("user");
            userMap.put("url",map.get("url"));
            List<String> codeList=roleFunDao.findCodeByRole(userMap);
            Map codeMap=new HashMap<>();
            if (codeList.size()!=0){
               for(String code:codeList){
                   codeMap.put(code,true);
               }
            }
            return resultSucess(codeMap);
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }

    }
}
