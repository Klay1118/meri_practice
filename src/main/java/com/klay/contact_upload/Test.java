package com.klay.contact_upload;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        // File file=new File("C:\\Users\\sales\\Downloads\\docs\\contact\\TotalName_test");
        //File srcfile=new File("C:\\Users\\sales\\Downloads\\docs\\contact\\contact-000000.json");
        String srcPath="C:\\Users\\sales\\Downloads\\docs\\contact\\contact-000000.json";
        String desPath="C:\\Users\\sales\\Downloads\\docs\\contact\\contact-000000_done.json";
        Contact_check(srcPath,desPath);

    }

    public static  void Contact_check(String srcFile ,String desFile){
        // 读取nameID.txt文件中的NAMEID字段（key）对应值（value）并存储
        ArrayList<String> list = new ArrayList<String>();
        BufferedReader brname;
        try {
            brname = new BufferedReader(new FileReader(srcFile));
            String sname = null;
            while ((sname = brname.readLine()) != null) {
                list.add(sname);// 将对应value添加到链表存储
            }
            brname.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        // 读取原始json文件并进行操作和输出
        try {
            BufferedReader br = new BufferedReader(new FileReader(srcFile));// 读取原始json文件
            BufferedWriter bw = new BufferedWriter(new FileWriter(desFile));// 输出新的json文件
            String s = null, ws = null;
            while ((s = br.readLine()) != null) {
                try {
                    JSONObject dataJson = new JSONObject(s);// 创建一个包含原始json串的json对象
                    JSONObject field_mapping = dataJson.getJSONObject("field_mapping");
                    field_mapping.put("custentity__placeholder","custentity__placeholder");
                    JSONArray features = dataJson.getJSONArray("data");// 找到contact的json数组
                    for (int i = 0; i < features.length(); i++) {
                        JSONObject info = features.getJSONObject(i);// 获取contact数组的第i个json对象
                        try {
                            //全名
                            String name = info.getString("姓名");
                            Random rand = new Random();
                            int nextInt = rand.nextInt(10000000);
                            info.put("姓名", name + "_00" + nextInt);

                        }catch (Exception e){
                            continue;
                        }
                    }
                    ws = dataJson.toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            bw.write(ws);
            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
