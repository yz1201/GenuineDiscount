package com.example.tyz1201.mytablayout.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by tyz1201 on 2016/4/20.
 */
public class NetworkRequest {
    public static String getRequestData(String netStr) {
        BufferedReader br = null;
        String gson = "";
        try {
            URL url = new URL(netStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String line = "";
            while ((line = br.readLine()) != null) {
                gson = line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return gson;
    }

    public static Bitmap getBitmap(final String url) {
        Bitmap bitmap = null;
        InputStream inputStream = null;
        try {
            URL picUrl = new URL(url);
            inputStream = picUrl.openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    public static String postRequestData(String urlStr, Map<String, String> pramas) {
        String json = "";
        BufferedReader bufferedReader = null;
        BufferedWriter bw = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();//开启一个网络连接
            conn.setRequestMethod("POST");

            String paramsStr = getParamsStr(pramas);
            //添加post请求的两行属性
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", paramsStr.length() + "");

            conn.setDoOutput(true);

            bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            bw.write(paramsStr);
            bw.flush();

            InputStream inputStream = conn.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));//获取缓冲字符输入流
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                json += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return json;
    }

    public static String getParamsStr(Map<String, String> params) {
        String paramsStr = "";
        for (Map.Entry<String, String> entry : params.entrySet()) {
            paramsStr += entry.getKey() + "=" + URLEncoder.encode(entry.getValue()) + "&";
        }
        paramsStr = paramsStr.substring(0, paramsStr.length() - 1);
        return paramsStr;
    }

}