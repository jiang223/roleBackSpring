package com.model;


import java.util.Map;

/**
 * Created by Administrator on 2017-08-18.
 */
public class Pagination {
    public Pagination(Map map){
        this.page=Integer.valueOf((String)map.get("page"));
        this.pageSize=Integer.valueOf((String)map.get("pageSize"));
    }

    public  int page;
    public  int pageSize;
    public  long total;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
