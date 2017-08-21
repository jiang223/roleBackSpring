package com.controller;

import com.dao.RoleFunDao;
import com.util.MyException;
import com.util.ValidatePermission;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.AccessDeniedException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Component
@Aspect
public class AdviceTest {
    @Resource
    RoleFunDao roleFunDao;
    @Around("execution( * com.gjj.controller.*.*(..))")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        System.out.println("@Around：执行目标方法之前...");
        //访问目标方法的参数：
        Object[] args = point.getArgs();
        if (args!=null){
            for(int i=0;i<args.length;i++){
                System.out.print(args[i].getClass());
                if(args[i]!=null&&args[i].getClass()== LinkedHashMap.class){
                    System.out.println("@Around：执行目标"+point.getTarget().getClass().getName()+"方法之前解密...");
                    for(Map.Entry entry : ((Map<String,Object>)args[i]).entrySet())
                    {

                    }
                }
            }
        }
        //用改变后的参数执行目标方法
        Object returnValue = point.proceed(args);
        System.out.println("@Around：执行目标方法之后...");
        System.out.println("@Around：被织入的目标对象为：" + point.getTarget());
        return returnValue;

    }
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
            String url = request.getRequestURL().toString();
            String method = request.getMethod();
            String uri = request.getRequestURI();
            HttpSession session=request.getSession();
            ValidatePermission oper = soruceMethod.getAnnotation(ValidatePermission.class);
            if (oper != null) {
                Map userMap=(Map) session.getAttribute("user");
                List<String> funList= roleFunDao.findFunByRole(userMap);
                if(funList.contains(uri))return returnValue;
                int fIdx = oper.idx();
                if (fIdx>= 0 &&fIdx<args.length){
                    //int functionId = (Integer) args[fIdx];
                    /*String rs = sysUserRolePermService.permissionValidate(functionId);
                    System.out.println("permissionValidate:"+rs);
                    if(rs.trim().isEmpty()){
                        return ;//正常
                    }*/
               }
               throw new MyException("您无权操作！");
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
}