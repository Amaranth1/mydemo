package com.ncs.ivh.flow.test.dao;

import com.ncs.ivh.flow.test.model.Event;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EventDao
{
    public List<Event> findAll();
}
