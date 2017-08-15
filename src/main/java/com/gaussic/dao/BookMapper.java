package com.gaussic.dao;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * Created by a9805943 on 2017/4/15.
 */
public interface BookMapper {
    void batchInsert( List<String> bookUrl);
}
