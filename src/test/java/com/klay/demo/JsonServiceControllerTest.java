package com.klay.demo;


import com.alibaba.fastjson.JSONObject;
import com.klay.utils.LogDemo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;


import static com.klay.utils.ReadJsonFile.readJsonDir;

public class JsonServiceControllerTest extends  AbstractTest {
    @Before
    @Override
    public void setUp() {
        super.setUp();
    }

    @Autowired
    private RestTemplate restTemplate;

     LogDemo logDemo=new LogDemo();
    @Test
    public void doPost() throws Exception {
        String url = "http://localhost:8080/test/postApi";
        String filename = "C:\\Users\\sales\\Downloads\\docs\\20181205_1831\\data\\customer-000000.json";
        //读取json文件，返回string类型
        final String str = readJsonDir(filename);
        System.out.println(str);
        //设置http头部类型和编码
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);

        final HttpEntity<String> httpEntity =
                new HttpEntity<String>(str, headers);
        //调用exchange方法，返回responseEntity实体
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        System.out.println("----------------");
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        logDemo.logToFile(responseEntity,url);

    }

}
