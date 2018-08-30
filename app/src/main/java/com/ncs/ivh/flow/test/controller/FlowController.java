package com.ncs.ivh.flow.test.controller;

import com.ncs.ivh.flow.test.model.Event;
import com.ncs.ivh.flow.test.model.Page;
import com.ncs.ivh.flow.test.util.FileUtil;
import com.ncs.ivh.flow.test.util.HttpClientUtil;
import com.ncs.ivh.flow.test.util.JsonMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

    @RequestMapping("/upload")
    public String upload(){
        return "upload";
    }

    @RequestMapping("/analytics")
    public String analytics(){
        return "analytics";
    }
    @RequestMapping("/getTableData")
    @ResponseBody
    public String getTableData(){
        List<Event> list = new ArrayList<>();
        list.add(new Event("Event1","1",new Date(),"aa"));
        list.add(new Event("Event2","2",new Date(),"11"));
        list.add(new Event("Event3","3",new Date(),"22"));
        list.add(new Event("Event4","4",new Date(),"33"));
        list.add(new Event("Event5","5",new Date(),"11"));
        list.add(new Event("Event6","6",new Date(),"11"));
        list.add(new Event("Event1","1",new Date(),"aa"));
        list.add(new Event("Event2","2",new Date(),"11"));
        list.add(new Event("Event3","3",new Date(),"22"));
        list.add(new Event("Event4","4",new Date(),"33"));
//        list.add(new Event("Event5","5",new Date(),"11"));
//        list.add(new Event("Event6","6",new Date(),"11"));
//        list.add(new Event("Event1","1",new Date(),"aa"));
//        list.add(new Event("Event2","2",new Date(),"11"));
//        list.add(new Event("Event3","3",new Date(),"22"));
//        list.add(new Event("Event4","4",new Date(),"33"));
//        list.add(new Event("Event5","5",new Date(),"11"));
//        list.add(new Event("Event6","6",new Date(),"11"));
        String result = null;
        try{
            Page<Event> page = new Page<>();
            page.setRows(list);
            page.setPageNumber(1);
            page.setPageSize(10);
            page.setTotal(123);
            result = JsonMapper.object2Json(page);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/testUpload")
    @ResponseBody
    public String testUpload(MultipartFile file, String name, String address){
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("address",address);
        System.out.println("============testUpload");
        File f = null;
        try{
            f = FileUtil.MultipartFile2File(file);
            System.out.println(f.getName());
            System.out.println(f.getAbsolutePath());
            HttpClientUtil.doFormData("http://localhost:8099/flow/testUploadApiCall",f,map);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }finally
        {
            FileUtil.deleteFile(f);
        }
        return "success";
    }

    @RequestMapping("/testUploadApiCall")
    @ResponseBody
    public String testUpload2(MultipartFile file,Map<String,String> map){
        System.out.println("============testUploadApiCall");
        for (String key:map.keySet()){
            System.out.println("key:"+key+", value:"+map.get(key));
        }
        try{
            FileUtil.MultipartFile2File(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "111";
    }

}
