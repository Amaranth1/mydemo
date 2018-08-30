package com.ncs.ivh.flow.test.model;

import java.util.List;

public class Page<T>
{
    List<T> rows;
    int pageSize;
    int pageNumber;
    int total;


    public List<T> getRows()
    {
        return rows;
    }

    public void setRows(List<T> data)
    {
        this.rows = data;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getPageNumber()
    {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber)
    {
        this.pageNumber = pageNumber;
    }

    public int getTotal()
    {
        return total;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }
}
