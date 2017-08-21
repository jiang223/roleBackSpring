package com.controller;


import com.dao.UserDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.model.Pagination;
import com.util.ParentController;
import com.util.ValidatePermission;
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
    @ValidatePermission
    public Map get(HttpSession session, @RequestParam Map map)
    {
        try {
            Pagination pagination=new Pagination(map);
            PageHelper.startPage(pagination.getPage(), pagination.getPageSize());
            //用PageInfo对结果进行包装
            List<Map> list=userDao.findUserPage();
            pagination.setTotal(new PageInfo(list).getTotal());
            return resultSucess(list,pagination);
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
            if(userDao.insertSelective(map)>0){
                return resultSucess("添加成功");
            }else{
                return resultError("未添加成功");
            }
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
            if(userDao.updateByPrimaryKeySelective(map)>0){
                return resultSucess("修改成功");
            }else{
                return resultError("修改失败");
            }

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
