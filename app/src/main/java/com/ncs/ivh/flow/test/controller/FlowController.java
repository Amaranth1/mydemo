package com.ncs.ivh.flow.test.controller;

import com.ncs.ivh.flow.test.util.FileUtil;
import com.ncs.ivh.flow.test.util.HttpClientUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping("/testUpload")
    @ResponseBody
    public String testUpload(MultipartFile file, String name, String address){
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("address",address);
        System.out.println("============testUpload");
//        File f = (File) file;
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
