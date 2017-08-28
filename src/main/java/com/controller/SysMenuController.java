package com.controller;


import com.dao.FunDao;
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
@RequestMapping("/sysMenu")
@ValidatePermission
public class SysMenuController extends ParentController {
    @Resource
    FunDao funDao;

    /**
     * 菜单页面数据
     * @param session
     * @param map
     * @return
     */
    @RequestMapping(method =RequestMethod.GET)
    @ResponseBody
    public Map get(HttpSession session, @RequestParam Map<String,Object>map)
    {
        try {
            List<Map> list = funDao.findMenuAll();
            return resultSucess(list);
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }

    }

    /**
     * 新增菜单
     * @param session
     * @param map
     * @return
     */
    @RequestMapping(method =RequestMethod.POST)
    @ResponseBody
    public Map add(HttpSession session, @RequestBody Map<String,Object>map)
    {
        try {
            funDao.insertSelective(map);
            String route;
            if ("".equals((String)map.get("route"))){
                 route=map.get("id").toString();
            }else{
                 route=(String)map.get("route")+"-"+map.get("id").toString();
            }
            map.put("route",route);
            funDao.updateByPrimaryKeySelective(map);
            return resultSucess("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }

    }

    /**
     * 更新菜单
     * @param session
     * @param map
     * @return
     */
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

    /**
     * 删除菜单
     * @param session
     * @param map
     * @return
     */
    @RequestMapping(method =RequestMethod.DELETE)
    @ResponseBody
    public Map delete(HttpSession session, @RequestBody Map<String,Object>map)
    {
        try {
            int count= funDao.countMenuByparentId(map);
            int rcount= funDao.countRoleByFunId(map);
            if (count>0){
                return resultError("请先删除子菜单后执行该操作");
            }
            if(rcount>0){
                return resultError("请先将相应角色管理取消后执行该操作");
            }
            funDao.deleteById(map);
            return resultSucess("删除成功");
        }catch (Exception e){
            e.printStackTrace() ;
            return resultError("失败"+e);
        }

    }
}
