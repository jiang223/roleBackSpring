package com.controller;


import com.dao.FunDao;
import com.util.ParentController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Administrator on 2017-08-16.
 */
@Controller
@RequestMapping("/sysMenu")
public class SysMenuController extends ParentController {
    @Resource
    FunDao funDao;

    @RequestMapping(method =RequestMethod.POST)
    @ResponseBody
    public Map add(HttpSession session, @RequestBody Map<String,Object>map)
    {
        try {
            funDao.insertSelective(map);
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
            funDao.updateByPrimaryKeySelective(map);
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
        return  resultSucess("");

    }
}
