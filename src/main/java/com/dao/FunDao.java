package com.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface FunDao {
  void insertSelective(Map map);
  void updateByPrimaryKeySelective(Map map);
}