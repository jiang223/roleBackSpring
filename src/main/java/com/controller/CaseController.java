package com.controller;


import com.dao.CaseDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.model.Pagination;
import com.util.ParentController;
import com.util.ValidatePermission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by 666 on 2018-04-21
 */
@Controller
@RequestMapping("/case")
@ValidatePermission
public class CaseController extends ParentController {
    @Resource
    CaseDao caseDao;

    /**
     *分页数据获取
     * @param map
     * @return
     */
    @RequestMapping(method =RequestMethod.GET)
    @ResponseBody
    @ValidatePermission(vali = false)
    public Map getPage(@RequestParam Map map)
    {
        try {
            Pagination pagination=new Pagination(map);
            PageHelper.startPage(pagination.getPage(), pagination.getPageSize());
            //用PageInfo对结果进行包装
            List<Map> list=caseDao.findParmPage(map);
            pagination.setTotal(new PageInfo(list).getTotal());
            return resultSucess(list,pagination);
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }

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
            if(caseDao.insertSelective(map)>0){
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
            if(caseDao.updateByPrimaryKeySelective(map)>0){
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
            caseDao.deleteById(map);
            return resultSucess("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return resultError("失败"+e);
        }

    }
}
