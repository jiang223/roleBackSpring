package com.dao;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserDao {
    List<Map> findUserPage();
    void insertSelective(Map map);
    void updateByPrimaryKeySelective(Map map);
    void  deleteById(Map map);
}