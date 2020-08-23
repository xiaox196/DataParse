package com.ming.data.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtil {

    private static RequestConfig requestConfig = null;

    static {
        // 设置请求和传输超时时间
        requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
    }


    /**
     * post请求传输json参数
     *
     * @param url url地址
     * @param
     * @return
     */
    public static String httpPostString(String url, JSONObject jsonParam) {
        // post请求返回结果  
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String jsonResult = null;
        HttpPost httpPost = new HttpPost(url);
        // 设置请求和传输超时时间  
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(2000).setConnectTimeout(2000).build();
        httpPost.setConfig(requestConfig);
        try {
            if (null != jsonParam) {
                // 解决中文乱码问题  
                StringEntity entity = new StringEntity(jsonParam.toString(),
                        "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }
            CloseableHttpResponse result = httpClient.execute(httpPost);
            //请求发送成功，并得到响应  
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String str = "";
                try {
                    //读取服务器返回过来的json字符串数据   
                    jsonResult = EntityUtils.toString(result.getEntity(), "utf-8");

                } catch (Exception e) {
                    System.out.println("请求失败");
                }
            }
        } catch (IOException e) {
            System.out.println("shibai");
        } finally {
            httpPost.releaseConnection();
        }
        return jsonResult;
    }

    /**
     * post请求传输json参数
     *
     * @param url url地址
     * @param
     * @return
     */
    public static JSONObject httpPost(String url, JSONObject jsonParam) {
        // post请求返回结果  
        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONObject jsonResult = null;
        HttpPost httpPost = new HttpPost(url);
        // 设置请求和传输超时时间  
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(2000).setConnectTimeout(2000).build();
        httpPost.setConfig(requestConfig);
        try {
            if (null != jsonParam) {
                // 解决中文乱码问题  
                StringEntity entity = new StringEntity(jsonParam.toString(),
                        "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }
            CloseableHttpResponse result = httpClient.execute(httpPost);
            //请求发送成功，并得到响应  
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String str = "";
                try {
                    //读取服务器返回过来的json字符串数据   
                    str = EntityUtils.toString(result.getEntity(), "utf-8");
                    //把json字符串转换成json对象   
                    jsonResult = JSONObject.parseObject(str);
                } catch (Exception e) {
                    System.out.println("请求失败");
                }
            }
        } catch (IOException e) {
            System.out.println("shibai");
        } finally {
            httpPost.releaseConnection();
        }
        return jsonResult;
    }


    /**
     * post请求传输String参数
     * 例如：name=Jack&sex=1&type=2
     * Content-type:application/x-www-form-urlencoded
     *
     * @param url      url地址
     * @param strParam 参数
     * @param
     * @return
     */
    public static JSONObject httpPost(String url, String strParam) {
        // post请求返回结果  
        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONObject jsonResult = null;
        HttpPost httpPost = new HttpPost(url);
        // 设置请求和传输超时时间  
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(2000).setConnectTimeout(2000).build();
        httpPost.setConfig(requestConfig);
        try {
            if (null != strParam) {
                // 解决中文乱码问题  
                StringEntity entity = new StringEntity(strParam, "utf-8");
                entity.setContentEncoding("UTF-8");
                httpPost.setEntity(entity);
            }
            CloseableHttpResponse result = httpClient.execute(httpPost);
            //请求发送成功，并得到响应  
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String str = "";
                try {
                    //读取服务器返回过来的json字符串数据  
                    str = EntityUtils.toString(result.getEntity(), "utf-8");
                    //把json字符串转换成json对象  
                    jsonResult = JSONObject.parseObject(str);
                } catch (Exception e) {
                    System.out.println("post请求提交失败" + e);
                }
            }
        } catch (IOException e) {
            System.out.println("");
        } finally {
            httpPost.releaseConnection();
        }
        return jsonResult;
    }


    /**
     * 发送get请求
     *
     * @param url 路径
     * @return
     */
    public static String  httpGet(String url) {
        System.out.println("url:"+url);
        // get请求返回结果
        String strResult=null;
        CloseableHttpClient client = HttpClients.createDefault();
        // 发送get请求
        HttpGet request = new HttpGet(url);
        request.setConfig(requestConfig);
        try {
            CloseableHttpResponse response = client.execute(request);

            // 请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 读取服务器返回过来的json字符串数据
                HttpEntity entity = response.getEntity();
                strResult = EntityUtils.toString(entity, "utf-8");
            } else {

            }
        } catch (IOException e) {
        } finally {
        }
        return strResult;
    }

    public static void main(String[] args) {
        httpGet("https://www.baidu.com/");
    }
}
