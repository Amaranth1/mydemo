package com.ncs.ivh.flow.test.util;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A HttpClient tool ,send request with get/pots method
 * and get return information as String
 */
public final class HttpClientUtil
{
    private Logger logger = LogManager.getLogger(HttpClientUtil.class);

    public static String doGet(String url, Map<String, String> params) throws Exception
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = null;
        CloseableHttpResponse httpResponse = null;
        URIBuilder builder = new URIBuilder(url);
        if (params != null)
        {
            for (String key : params.keySet())
            {
                builder.addParameter(key, params.get(key));
            }
        }

        URI uri = builder.build();
        HttpGet httpGet = new HttpGet(uri);
        httpResponse = httpClient.execute(httpGet);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == 200)
        {
            result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        }
        releaseResource(httpClient, httpResponse);
        return result;
    }


    public static String doPost(String url, Map<String, String> params) throws Exception
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        HttpPost httpPost = new HttpPost(url);
        if (params != null)
        {
            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
            for (String key : params.keySet())
            {
                paramList.add(new BasicNameValuePair(key, params.get(key)));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
            httpPost.setEntity(entity);
        }
        response = httpClient.execute(httpPost);
        resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        releaseResource(httpClient, response);
        return resultString;
    }

    public static String doPut(String url, Map<String, String> params) throws Exception
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        HttpPut httpPut = new HttpPut(url);
        if (params != null)
        {
            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
            for (String key : params.keySet())
            {
                paramList.add(new BasicNameValuePair(key, params.get(key)));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
            httpPut.setEntity(entity);
        }
        response = httpClient.execute(httpPut);
        resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        releaseResource(httpClient, response);
        return resultString;
    }


    public static String doFormData(String url, File file, Map<String,String> params) throws Exception{
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(url);
       // FileBody fileBody = new FileBody(file);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        //multipartEntityBuilder.addPart("file",fileBody);
        multipartEntityBuilder.addBinaryBody("file",file,ContentType.DEFAULT_BINARY,file.getName());
        for (String key:params.keySet()){
//            StringBody stringBody = new StringBody(params.get(key),ContentType.create("text/plain", Consts.UTF_8));
//            multipartEntityBuilder.addPart(key,stringBody);
            multipartEntityBuilder.addTextBody(key,params.get(key),ContentType.TEXT_PLAIN);
        }
        HttpEntity httpEntity = multipartEntityBuilder.build();
        httpPost.setEntity(httpEntity);
        response = httpClient.execute(httpPost);
        result = EntityUtils.toString(response.getEntity(),Consts.UTF_8);
        EntityUtils.consume(httpEntity);
        releaseResource(httpClient,response);
        return result;
    }


    public static String doPostJson(String url, String json) throws Exception
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        response = httpClient.execute(httpPost);
        resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        releaseResource(httpClient, response);
        return resultString;
    }

    private static void releaseResource(CloseableHttpClient client, CloseableHttpResponse response) throws Exception
    {
        if (client != null)
        {
            client.close();
        }
        if (response != null)
        {
            response.close();
        }
    }

    public static void main(String[] args)
    {
        String url = "http://10.10.226.77:25030/info";
        try
        {
            String result = HttpClientUtil.doGet(url, null);
            System.out.println(result);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
