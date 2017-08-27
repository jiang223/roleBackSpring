package com.util;


import com.controller.SysMenuController;
import org.apache.ibatis.cache.Cache;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 创建时间：2016年1月7日 上午11:40:00 
 *  
 * Mybatis二级缓存实现类 
 *  
 * @author andy 
 * @version 2.2 
 */

  
public class MybatisRedisCache implements Cache {
  
    private static final Logger LOG = Logger.getLogger(MybatisRedisCache.class);
      
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
      
    private RedisTemplate<Serializable, Serializable> redisTemplate;
    //private RedisTemplate<Serializable, Serializable> redisTemplate =  new RedisTemplate();
    private String id;  
      
    private JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer();
      
    public MybatisRedisCache(final String id){
        if(id == null){  
            throw new IllegalArgumentException("Cache instances require an ID");  
        }  
        LOG.info("Redis Cache id " + id);  
        this.id = id;  
    }  
      
    @Override  
    public String getId() {  
        return this.id;  
    }  
  
    @Override  
    public void putObject(Object key, Object value) {
        redisTemplate=(RedisTemplate<Serializable, Serializable>) SpringContextHolder.getBean("redisTemplate");
        //SysMenuController MENU=SpringContextHolder.getBean(SysMenuController.class);
        //redisTemplate=(RedisTemplate<Serializable, Serializable>) SpringContextHolder.getBean(RedisTemplate.class);
        if(value != null){  
            redisTemplate.opsForValue().set(key.toString(), jdkSerializer.serialize(value), 2, TimeUnit.DAYS);  
        }  
    }  
  
    @Override  
    public Object getObject(Object key) {
       // RedisTemplate MENU=(RedisTemplate)SpringContextHolder.getBean(RedisTemplate.class);
        //redisTemplate=(RedisTemplate<Serializable, Serializable>) SpringContextHolder.getBean("redisTemplate");
        try {  
            if(key != null){  
                Object obj = redisTemplate.opsForValue().get(key.toString());  
                return jdkSerializer.deserialize((byte[])obj);   
            }  
        } catch (Exception e) {  
            LOG.error("redis ");  
        }  
        return null;  
    }  
  
    @Override  
    public Object removeObject(Object key) {
        //redisTemplate=(RedisTemplate<Serializable, Serializable>) SpringContextHolder.getBean("redisTemplate");
        try {  
            if(key != null){  
                redisTemplate.expire(key.toString(), 1, TimeUnit.SECONDS);
            }  
        } catch (Exception e) {  
        }  
        return null;  
    }  
  
    @Override  
    public void clear() {  
        //jedis nonsupport  
    }  
  
    @Override  
    public int getSize() {
        //redisTemplate=(RedisTemplate<Serializable, Serializable>) SpringContextHolder.getBean("redisTemplate");
        Long size = redisTemplate.execute(new RedisCallback<Long>(){
            @Override  
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException {
                return connection.dbSize();  
            }  
        });  
        return size.intValue();  
    }  
  
    @Override  
    public ReadWriteLock getReadWriteLock() {  
        return this.readWriteLock;  
    }  
      
}  