package com.ncs.ivh.flow.test.util;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public final class FileUtil
{
    private final static String TEMP_PATH = "/temp/";


    public static File MultipartFile2File(MultipartFile multipartFile) throws Exception{
        mkDir();
        InputStream inputStream = multipartFile.getInputStream();
        String fileName = multipartFile.getOriginalFilename();
        File file = new File(TEMP_PATH+fileName);
        OutputStream outputStream = new FileOutputStream(file);
        FileCopyUtils.copy(inputStream,outputStream);
        return file;
    }

    public static void deleteFile(File file){
        file.delete();
    }

    public static void mkDir(){
        File file = new File(TEMP_PATH);
        if(!file.exists()){
            file.mkdir();
        }
    }
}
