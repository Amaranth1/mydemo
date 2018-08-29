package com.ncs.ivh.flow.test.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("flow")
public class FlowController
{

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

    @RequestMapping("/overView")
    public String overView(){
        return "overView";
    }


}
