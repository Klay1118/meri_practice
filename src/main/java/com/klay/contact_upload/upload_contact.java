package com.klay.contact_upload;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class upload_contact {
    public static void main(String[] args) {
       // File file=new File("C:\\Users\\sales\\Downloads\\docs\\contact\\TotalName_test");
        File file=
                new File("C:\\Users\\sales\\Downloads\\docs\\" +
                        "contact\\20181220_1125_new_Sandbox\\data");
        File[] files = file.listFiles();
        for (File f:files) {
            String srcPath = f.getAbsolutePath();
            String desPath=
                    "C:\\Users\\sales\\Downloads\\docs\\contact\\20181220_1125_new_Sandbox_done\\"
                            +f.getName();
            Contact_check(srcPath,desPath);
        }

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
                           // UUID uuid = UUID.randomUUID();
                            Random rand = new Random();
                            int nextInt = rand.nextInt(10000000);
                            // info.put("联系人ID",uuid);
                            //info.put("公司名称", "C00991708 SandBox系统预置客户");
                           // info.put("公司ID", "5cf68aa4-2b0b-4af0-82fe-97d922c73142");
                            info.put("姓名", name + "_0" + nextInt);
                            //info.put("custentity__placeholder","custentity__placeholder");
                            //内部id
                            /*String name = info.getString("姓名");
                            UUID uuid = UUID.randomUUID();
                            Random rand = new Random();
                            int nextInt = rand.nextInt(10000000);
                            info.put("联系人ID", uuid);
                            info.put("公司ID", "b77a35e1-8439-4531-939d-ba3a988cd849");
                            info.put("公司内部ID", "1178932");
                            info.put("姓名", name + "_00" + nextInt);
                            info.remove("公司名称");*/
                        }catch (Exception e){
                            //联系人没有姓名
                            /*info.put("公司名称", "C00991708 SandBox系统预置客户");
                            info.put("公司ID", "5cf68aa4-2b0b-4af0-82fe-97d922c73142");*/
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
