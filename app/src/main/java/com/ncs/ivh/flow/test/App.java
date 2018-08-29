package com.ncs.ivh.flow.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.ncs.ivh.flow.test")
public class App
{
    public static void main(String[] args)
    {
        try{
            SpringApplication.run(App.class,args);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
