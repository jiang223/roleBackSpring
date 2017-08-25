package com.dao;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RoleDao {
    List<Map> findPage(Map map);
    void insertSelective(Map map);
    void updateByPrimaryKeySelective(Map map);
    void deleteById(Map map);
}