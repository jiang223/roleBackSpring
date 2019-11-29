package com.controller;


import com.dao.NewsDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.model.Pagination;
import com.util.ParentController;
import com.util.ValidatePermission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by 666 on 2018-04-21
 */
@Controller
@RequestMapping("/news")
public class NewsController extends ParentController {
    @Resource
    NewsDao newsDao;

    /**
     *分页数据获取
     * @param map
     * @return
     */
    @RequestMapping(method =RequestMethod.GET)
    @ResponseBody
    public Map getPage(@RequestParam Map map)
    {

        try {
            Pagination pagination=new Pagination(map);
            PageHelper.startPage(pagination.getPage(), pagination.getPageSize());
            //用PageInfo对结果进行包装
            List<Map> list=newsDao.findParmPage(map);
            pagination.setTotal(new PageInfo(list).getTotal());
            return resultSucess(list,pagination);
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }

    }
    /**
     *分页数据获取
     * @param map
     * @return
     */
    @RequestMapping(method =RequestMethod.GET,value = "/get")
    @ResponseBody
    public Map get(@RequestParam Map map)
    {
        try {
            Map map2=newsDao.findParm(map);
            return resultSucess(map2);
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }

    }
    /**
     * 删除
     * @param map
     * @return
     */
    @RequestMapping(method =RequestMethod.GET,value = "/get2")
    @ResponseBody
    public ModelAndView get2 ()
    {
        ModelAndView mav=new ModelAndView();
        mav.setViewName("redirect:http://api.tongtongtingche.com.cn/MobileServer/home/fee_query.htm");
        return mav;

    }
    /**
     * 新增
     * @param map
     * @return
     */
    @RequestMapping(method =RequestMethod.POST)
    @ResponseBody
    public Map add(@RequestBody Map<String,Object>map)
    {
        try {
            if(newsDao.insertSelective(map)>0){
                return resultSucess("添加成功");
            }else{
                return resultError("未添加成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }

    }

    /**
     * 更新
     * @param map
     * @return
     */
    @RequestMapping(method =RequestMethod.PATCH)
    @ResponseBody
    public Map update(@RequestBody Map<String,Object>map)
    {
        try {
            if(newsDao.updateByPrimaryKeySelective(map)>0){
                return resultSucess("修改成功");
            }else{
                return resultError("修改失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }

    }

    /**
     * 删除
     * @param map
     * @return
     */
    @RequestMapping(method =RequestMethod.DELETE)
    @ResponseBody
    public Map delete(@RequestBody Map<String,Object>map)
    {
        try {
            newsDao.deleteById(map);
            return resultSucess("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }

    }
}
