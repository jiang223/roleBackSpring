package com.controller;


import com.dao.UserDao;
import com.util.ParentController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-08-16.
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController extends ParentController {
    @Resource
    UserDao userDao;

    @RequestMapping(method =RequestMethod.GET)
    @ResponseBody
    public Map get(HttpSession session, @RequestParam Map<String,Object>map)
    {
        try {

            List<Map> list=userDao.findUserPage();
            return resultSucess(list);
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }

    }
    @RequestMapping(method =RequestMethod.POST)
    @ResponseBody
    public Map add(HttpSession session, @RequestBody Map<String,Object>map)
    {
        try {
            userDao.insertSelective(map);
            String route;
            if ("".equals(map.get("route"))){
                 route=map.get("id").toString();
            }else{
                 route=map.get("route")+"-"+map.get("id").toString();
            }
            map.put("route",route);
            userDao.updateByPrimaryKeySelective(map);
            return resultSucess("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }

    }

    @RequestMapping(method =RequestMethod.PATCH)
    @ResponseBody
    public Map update(HttpSession session, @RequestBody Map<String,Object>map)
    {
        try {
            userDao.updateByPrimaryKeySelective(map);
            return resultSucess("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }

    }
    @RequestMapping(method =RequestMethod.DELETE)
    @ResponseBody
    public Map delete(HttpSession session, @RequestBody Map<String,Object>map)
    {
        try {
            userDao.deleteById(map);
            return resultSucess("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }

    }
}
