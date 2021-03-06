package com.dao;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface FunDao {
  List<Map> findMenuAll();
  void insertSelective(Map map);
  void updateByPrimaryKeySelective(Map map);
  void  deleteById(Map map);
  int countMenuByparentId(Map map);
  int countRoleByFunId(Map map);
}