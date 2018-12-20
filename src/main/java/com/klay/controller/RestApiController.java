package com.klay.controller;


import com.alibaba.fastjson.JSONObject;
import com.klay.utils.LogDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static com.klay.utils.ReadJsonFile.readJson;
import static com.klay.utils.ReadJsonFile.readJsonDir;

@RestController
@RequestMapping(value = "/test")
public class RestApiController {

    @Autowired
    private RestTemplate restTemplate;

    public static LogDemo logDemo = new LogDemo();

    @RequestMapping("/post")
    public ResponseEntity doPost(@RequestHeader("Authorization") String authorization) throws IOException {
        String url = "https://5179288-sb1.restlets.api.netsuite.com/app/site/hosting/restlet.nl?script=158&deploy=5";
        //String url="http://localhost:8080/test/postApi";
        //String url="http://rest.youngfey.com:8080/netsuite/api?script=158&deploy=5&user=dai";
        //文件目录
        String fileDir = "C:\\Users\\sales\\Downloads\\docs\\20181206_1252\\data";
        //读取json文件，返回string类型
        //final String str = readJsonDir(fileDir);
        String fileName = "C:\\Users\\sales\\Downloads\\docs\\20181206_1422\\data\\customer-000004.json";
        String str = readJson(fileName);
        System.out.println(str);
        //设置http头部类型和编码
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Authorization", authorization);

        HttpEntity<String> httpEntity =
                new HttpEntity<String>(str, headers);
        //调用exchange方法，返回responseEntity实体
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        System.out.println("-----------返回响应实体");
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        logDemo.logToFile(responseEntity, url);
        return responseEntity;

    }

    /**
     * 测试url
     *
     * @param param
     * @return
     */
    @RequestMapping("/postApi")
    public Object postJson(@RequestBody JSONObject param) {
        System.out.println(param.toJSONString());
        return param;
    }
}
