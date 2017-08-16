package com.dao;

import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public interface FunDao {
  void insertSelective(Map map);
  void updateByPrimaryKeySelective(Map map);
}