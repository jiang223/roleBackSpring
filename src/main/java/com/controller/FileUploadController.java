package com.controller;

import com.dao.FileUploadDao;
import com.util.ParentController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/fileUpload")
public class FileUploadController extends ParentController {
    @Resource
    FileUploadDao fileDao;
    //上传文件会自动绑定到MultipartFile中
    @RequestMapping(method= RequestMethod.POST)
    @ResponseBody
    public Map upload(HttpServletRequest request,
                         @RequestParam("avatar") MultipartFile file) throws Exception {
        try {
            //如果文件不为空，写入上传路径
            if (file.isEmpty()) {
                return  null;
            }
                //上传文件路径
                String path = request.getSession().getServletContext().getRealPath("/updload/file/");
                //上传文件名
                String filename = file.getOriginalFilename();
                File filepath = new File(path, filename);
                //判断路径是否存在，如果不存在就创建一个
                if (!filepath.getParentFile().exists()) {
                    filepath.getParentFile().mkdirs();
                }
                //将上传文件保存到一个目标文件当中
                file.transferTo(new File(path + File.separator + filename));
                Map map =new HashMap();
                map.put("fileName",filename);
                map.put("fileUrl","/updload/file/"+filename);


                fileDao.insertSelective(map);
                return resultSucess(map);
        } catch (Exception e) {
               return resultError("失败"+e);
        }
    }
}
