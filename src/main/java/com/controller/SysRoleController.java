package com.controller;

import com.dao.RoleDao;
import com.dao.RoleFunDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.model.Pagination;
import com.util.ParentController;
import com.util.ValidatePermission;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-08-16.
 */
@Controller
@RequestMapping("/sysRole")
@ValidatePermission
public class SysRoleController extends ParentController {
    @Resource
    RoleDao roleDao;
    @Resource
    RoleFunDao roleFunDao;

    /**
     * 角色管理分页数据
     * @param session
     * @param map
     * @return
     */
    @RequestMapping(method =RequestMethod.GET)
    @ResponseBody
    public Map getPage(HttpSession session, @RequestParam Map<String,Object>map)
    {
        try {
            Pagination pagination=new Pagination(map);
            PageHelper.startPage(pagination.getPage(), pagination.getPageSize());
            //用PageInfo对结果进行包装
            List<Map> list=roleDao.findPage(map);
            pagination.setTotal(new PageInfo(list).getTotal());
            return resultSucess(list,pagination);
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }
    }

    /**
     * 获取所有角色信息（用户用户下拉菜单）
     * @param session
     * @param map
     * @return
     */
    @RequestMapping(value = "findRole",method =RequestMethod.GET)
    @ResponseBody
    public Map findRole(HttpSession session, @RequestParam Map<String,Object>map)
    {
        try {
            List<Map> list=roleDao.findPage(map);
            return resultSucess(list);
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }
    }

    /**
     * 获取角色选中的菜单
     * @param session
     * @param map
     * @return
     */
    @RequestMapping(value = "getCheckMenu",method =RequestMethod.GET)
    @ResponseBody
    public Map getCheckMenu(HttpSession session, @RequestParam Map<String,Object>map)
    {
        try {
            List<String> list=roleFunDao.selectByRoleId(map);
            return resultSucess(list);
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }
    }

    /**
     * 角色新增
     * @param session
     * @param map
     * @return
     */
    @RequestMapping(method =RequestMethod.POST)
    @ResponseBody
    public Map add(HttpSession session, @RequestBody Map<String,Object>map)
    {
        try {
            roleDao.insertSelective(map);
            return resultSucess("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }

    }

    /**
     * 角色管理更新
     * @param session
     * @param map
     * @return
     */
    @RequestMapping(method =RequestMethod.PATCH)
    @ResponseBody
    public Map update(HttpSession session, @RequestBody Map<String,Object>map)
    {
        try {
            roleDao.updateByPrimaryKeySelective(map);
            return resultSucess("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }

    }

    /**
     * 角色管理更新菜单
     * @param session
     * @param map
     * @return
     */
    @RequestMapping(value = "/updateFunction" ,method =RequestMethod.PATCH)
    @ResponseBody
    @Transactional
    public Map updateFunction(HttpSession session, @RequestBody Map<String,Object>map)
    {
        try {
            roleFunDao.deleteById(map);
            roleFunDao.batchInsert(map);
            return resultSucess("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }

    }

    /**
     * 角色管理删除
     * @param session
     * @param map
     * @return
     */
    @RequestMapping(method =RequestMethod.DELETE)
    @ResponseBody
    public Map delete(HttpSession session, @RequestBody Map<String,Object>map)
    {
        try {
            int count=roleDao.countRoleUser(map);
            if(count>0){
               return resultError("请先在用户中移除该角色后执行该操作");
            }
            roleDao.deleteById(map);
            return resultSucess("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }

    }
}
