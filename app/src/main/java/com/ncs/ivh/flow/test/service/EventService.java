package com.ncs.ivh.flow.test.service;

import com.ncs.ivh.flow.test.dao.EventDao;
import com.ncs.ivh.flow.test.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventService
{
    @Autowired
    private EventDao eventDao;


    public List<Event> findAll(){
        return eventDao.findAll();
    }
}
