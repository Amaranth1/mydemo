package com.ncs.ivh.flow.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncs.ivh.flow.test.model.Event;
import com.ncs.ivh.flow.test.service.EventService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("flow")
public class FlowController
{
    @Autowired
    private EventService eventService;

    private Logger logger = LogManager.getLogger(FlowController.class);
    @RequestMapping("/index")
    public String index(){
        logger.info("++++++++++++++++++++++================");
        return "index";
    }

    @RequestMapping("/events")
    public String events(){
        logger.info("++++++++++++++++++++++events");
        return "events";
    }

    @RequestMapping("/eventList")
    @ResponseBody
    public String eventList(){
        ObjectMapper objectMapper = new ObjectMapper();
        List<Event> list = eventService.findAll();
        String result = null;
        try{
            result = objectMapper.writeValueAsString(list);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
