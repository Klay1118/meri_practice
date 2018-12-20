package com.klay.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogDemo {

    public void logToFile(ResponseEntity responseEntity, String url) {
        //得到responseEntity的返回状态码和返回体
        HttpStatus statusCode = responseEntity.getStatusCode();
        Object responseEntityBody = responseEntity.getBody();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        File file = new File("C:\\JsonLogger\\Jsonlog1.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), "UTF-8"));

            writer.write(sdf.format(new Date()) + "\t" +
                    url + "\t" + statusCode + "\r\n" + responseEntityBody+"\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("文件写入成功");
    }


}

