package com.klay.controller;

import com.alibaba.fastjson.JSONObject;
import com.klay.utils.ReadJsonFile;
import com.klay.send.SendPostMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

import static com.klay.utils.ReadJsonFile.readJson;

@RestController
public class SendPostController {

    ReadJsonFile readJsonFile = new ReadJsonFile();
    SendPostMethod sendPost = new SendPostMethod();

    @RequestMapping("test/postApiTest")
    public void doPost() throws IOException {
        //String url = "http://rest.youngfey.com:8080/netsuite/api?user=dai";
        String url="http://localhost:8080/test/postApitest1";
        File file = new File("C:\\Users\\sales\\Downloads\\docs\\20181206_1608\\data");
        File[] files = file.listFiles();
        for (File f : files) {
            String jsonData = readJson(f.getAbsolutePath());
            ResponseEntity responseEntity = sendPost.send(url, jsonData);
            System.out.println(responseEntity.getBody() + "------" + responseEntity.getStatusCode());

        }
    }

    @RequestMapping("/test/postApitest1")
    public Object postJson(@RequestBody JSONObject param) {
        System.out.println(param.toJSONString());
        return param;
    }

}
