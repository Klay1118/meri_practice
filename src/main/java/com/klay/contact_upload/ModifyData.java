package com.klay.contact_upload;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static com.klay.urlCorrect.ModifyWrongUrl.readJson;

public class ModifyData {
    public static void main(String[] args) throws IOException {
        String jsList = readJson("C:\\Users\\sales\\Downloads\\docs\\contact" +
                "\\20181217_1138\\data\\contact-000001.json");
        Modify(jsList);
    }

    public static void Modify(String jsdata) throws UnsupportedEncodingException {
        JSONObject jsonObject = JSONObject.parseObject
                (new String(jsdata.getBytes("utf-8"), "utf-8"));
        //找到json中名为data的数组
        JSONArray data = jsonObject.getJSONArray("data");
        for (int i = 0; i <data.size() ; i++) {
            JSONObject jOb1 = (JSONObject) data.get(i);
            String companyName = jOb1.getString("公司名称");
            System.out.println(companyName);
            String test = companyName.replaceAll(companyName, "C00991707 系统预置客户test");
            System.out.println(test);
        }

    }
}
