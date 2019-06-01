package com.dao;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ParmDao {
  List<Map> findParmPage(Map map);
  int insertSelective(Map map);
  int updateByPrimaryKeySelective(Map map);
  int  deleteById(Map map);
}