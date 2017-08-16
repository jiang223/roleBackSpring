package com.dao;


import java.util.List;

/**
 * Created by a9805943 on 2017/4/15.
 */
public interface BookMapper {
    void batchInsert( List<String> bookUrl);
}
