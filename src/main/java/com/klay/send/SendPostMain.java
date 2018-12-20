package com.klay.send;

import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;

import static com.klay.utils.ReadJsonFile.readJson;

/**
 * 发送post请求主方法
 */
public class SendPostMain {
        //String url="http://rest.youngfey.com:8080/netsuite/api?user=dai";
        //String url = "https://5179288-sb1.restlets.api.netsuite.com/app/site/hosting/restlet.nl?script=158&deploy=5";
        //String url="http://rest.youngfey.com:8080/netsuite/api?script=158&deploy=5&user=dai";
        static void send(int low, int high) throws IOException {
            String url = "http://localhost:8080/test/postApitest1";
            File file = new File("C:\\Users\\sales\\Downloads\\docs\\20181210_1109\\data");
            File[] files = file.listFiles();
            String path="C:\\Users\\sales\\Downloads\\docs\\20181210_1109\\data\\";
            SendPostMethod sendPost = new SendPostMethod();
            for (File f : files) {
                String jsonData = readJson(f.getAbsolutePath());
                String substring = f.getName().substring(9, 15);
                //json文件名后6位
                int num = Integer.parseInt(substring);
                File file1=new File(path+"customer-"+substring+".download");
                File file2=new File(path+"customer-"+substring+".error");
                if (f.getName().endsWith(".json")&&num >= low && num <= high) {
                    ResponseEntity responseEntity = sendPost.send(url, jsonData);
                    // System.out.println(responseEntity.getBody() + "------" + responseEntity.getStatusCode());
                    if (responseEntity.getStatusCodeValue()==200) {
                        boolean b = f.renameTo(file1);
                        if (b) System.out.println("发送成功");
                    }
                    else if (responseEntity.getStatusCodeValue()==500) {
                        f.renameTo(file2);
                        System.out.println("发送失败");
                    }
                }
            }
        }

}
