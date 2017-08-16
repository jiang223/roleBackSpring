package com.dao;

import java.util.Map;

/**
 * Created by a9805943 on 2017/3/18.
 */
public interface BlogService {
    void saveBlog(Map map);
    Map selectBlog(Map map);
}
