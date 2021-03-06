package com.dao;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RoleFunDao {
    List<String> findCodeByRole(Map map);
    List<String> selectByRoleId(Map map);
    List<String> findMethodByRole(Map map);
    int batchInsert(Map map);
    int deleteById(Map map);
    List<Map>findMenuByRole(Map map);
}