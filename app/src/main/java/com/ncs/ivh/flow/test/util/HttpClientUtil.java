package com.ncs.ivh.flow.test.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        String url = "http://localhost:8088/apis/getAllModules";
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
