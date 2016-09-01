package com.jdd.jiandandian.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 2016/5/2.
 */
public class HttpUtil {
    private static String api_key = "a4a44e58a0c1190e3292d5d1c14aec9b";
    private static String api_secret = "uRBe6xJ7S325G7mLLIStdOzPcrCTVFeB";
    //远程访问
    public final static String url = "http://www.yuanjingxueyuan.com";
    public final static String faceurle = "http://apicn.faceplusplus.com/v2/detection/detect";

    //face result json
    public static String faceResult;

    //本地访问
    //public final static String url="http://192.168.17.2";
    public static String upload(File file) {
        String result = "";
        Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            result = "文件找不到";
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 5, fos);
        try {
            fos.flush();
            fos.close();
        } catch (IOException e) {
            LogUtil.i("close error");
            result = "fos关闭异常";
            e.printStackTrace();
        }
        Log.i("tag2", "文件上传操作");
        //获得异步交互客户端对象
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        //请求参数对象
        RequestParams params = new RequestParams();
        try {
            params.put("img_test", file);
        } catch (FileNotFoundException e) {
            result = "文件找不到";
            e.printStackTrace();
        }
        asyncHttpClient.post(HttpUtil.url + "/MyAnnotation/servlet/Upload", params, new AsyncHttpResponseHandler() {
        });
        result = "文件上传成功";
        return result;
    }

    public static String detectFaceByFile(File file) {
        String result = "";
        Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            result = "文件找不到";
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 1, fos);
        try {
            fos.flush();
            fos.close();
        } catch (IOException e) {
            LogUtil.i("close error");
            result = "fos关闭异常";
            e.printStackTrace();
        }
        Log.i("tag2", "文件上传操作");
        //获得异步交互客户端对象
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        //请求参数对象
        RequestParams params = new RequestParams();
        params.put("api_key", api_key);
        params.put("api_secret", api_secret);
        try {
            params.put("img", file);
        } catch (FileNotFoundException e) {
            result = "文件找不到";
            e.printStackTrace();
        }
        asyncHttpClient.post(HttpUtil.faceurle, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i(result);
            }
        });
        result = "文件上传成功";
        return result;
    }

    public static String detectFaceByUrl(String turl) {
        faceResult = "FileUpload failed";
        //Log.i("tag2", "文件上传操作");
        //获得异步交互客户端对象
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        //请求参数对象
        RequestParams params = new RequestParams();
        params.put("api_key", api_key);
        params.put("api_secret", api_secret);
        params.put("url", turl);
        asyncHttpClient.post(HttpUtil.faceurle, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                LogUtil.i("statusCode:" + statusCode);
                faceResult = new String(responseBody);
                LogUtil.i("result:" + faceResult);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("statusCode:" + statusCode);
                faceResult = new String(responseBody);
                LogUtil.i("result:" + faceResult);
            }
        });
        //faceResult = "文件上传成功";
        return faceResult;
    }

    public static String test(String turl) {
        String path = "http://apicn.faceplusplus.com/v2/detection/detect";
        String json = "";
        HttpPost httpRequest = new HttpPost(path);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("api_key", api_key));
        params.add(new BasicNameValuePair("api_secret", api_secret));
        params.add(new BasicNameValuePair("url", turl));
        try {
            HttpEntity entity = new UrlEncodedFormEntity(params);
            httpRequest.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(httpRequest);
            LogUtil.i(response.getStatusLine().getStatusCode() + "");
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                json = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static void getServer() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8899);
            Socket socket = null;
            while (true) {
                LogUtil.i("Waiting connector");
                socket = serverSocket.accept();
                OutputStreamWriter os = new OutputStreamWriter(socket.getOutputStream());
                os.write(socket.getLocalPort() + ":" + socket.getPort() + " is coming");
                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                String result = inputStreamReader.toString();
                LogUtil.i(result);
                if (result.equals("exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            LogUtil.i("serverSocket open error!");
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                LogUtil.i("serverSocket close error!");
            }
        }

    }
}
