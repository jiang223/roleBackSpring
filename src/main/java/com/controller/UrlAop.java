package com.controller;

import com.dao.RoleFunDao;
import com.util.MyException;
import com.util.ValidatePermission;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;


@Component
@Aspect
public class UrlAop {
    @Resource
    RoleFunDao roleFunDao;

    @Around("execution( * com.controller.*.*(..))")
    public Object doBefore(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();
        Object returnValue = jp.proceed(args);
        System.out.println(
                "log PermissionAspect Before method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
        Method soruceMethod = getSourceMethod(jp);
        if(soruceMethod!=null){
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();
            String uri = request.getRequestURI();
            HttpSession session=request.getSession();
            ValidatePermission operc = getSourceClass(jp);
            ValidatePermission oper = soruceMethod.getAnnotation(ValidatePermission.class);
            if (operc != null) {
                if(oper==null||oper.vali()){
                    Map userMap=(Map) session.getAttribute("user");
                    List<String> funList= roleFunDao.findMethodByRole(userMap);
                    if(funList.contains(uri))return returnValue;
                    throw new MyException("您无权操作！");
                }

            }
        }
        return returnValue;
    }
    private Method getSourceMethod(JoinPoint jp){
        Method proxyMethod = ((MethodSignature) jp.getSignature()).getMethod();
        try {
            return jp.getTarget().getClass().getMethod(proxyMethod.getName(), proxyMethod.getParameterTypes());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }
    private ValidatePermission getSourceClass(JoinPoint jp){
        try {
            return jp.getTarget().getClass().getAnnotation(ValidatePermission.class);
        }
         catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}