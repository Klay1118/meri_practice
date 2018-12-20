package com.example.restful;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test/postApitest1")
    public Object postJson(@RequestBody JSONObject param) {
        System.out.println(param.toJSONString());
        return param;
    }
}
