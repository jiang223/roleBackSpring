package com.controller;


import com.dao.RoleFunDao;
import com.dao.UserDao;
import com.util.ParentController;
import com.util.ValidatePermission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户登录管理
 * Created by Administrator on 2017-08-16.
 */
@Controller
@RequestMapping("/user")
@ValidatePermission
public class UserController extends ParentController {
    @Resource
    UserDao userDao;
    @Resource
    RoleFunDao roleFunDao;

    /**
     * 登录
     * @param session
     * @param map
     * @return
     */
    @RequestMapping(method =RequestMethod.POST,value = "/login")
    @ResponseBody
    @ValidatePermission(vali = false)
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

    /**
     * 退出
     * @param session
     * @return
     */
    @RequestMapping(method =RequestMethod.GET,value = "/logout")
    @ResponseBody
    @ValidatePermission(vali = false)
    public Map logout(HttpSession session)
    {
        try {
            Map userMap=(Map) session.getAttribute("user");
            if (userMap!=null){
                session.setAttribute("user",null);
                return resultSucess(userMap);
            }else{
                return resultError("账号或密码错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }

    }

    /**
     * 验证是否为登录状态
     * @param session
     * @return
     */
    @RequestMapping(method =RequestMethod.GET)
    @ResponseBody
    @ValidatePermission(vali = false)
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

    /**
     * 获取当前用户菜单
     * @param session
     * @return
     */
    @RequestMapping(method =RequestMethod.GET,value = "/menu")
    @ResponseBody
    @ValidatePermission(vali = false)
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

    /**
     * 获取当前用户对页面操作code
     * @param session
     * @param map
     * @return
     */
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
