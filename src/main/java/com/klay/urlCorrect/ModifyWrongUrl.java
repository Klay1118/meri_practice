package com.klay.urlCorrect;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ModifyWrongUrl {
    public static void main(String[] args) throws IOException {
        ModifyWrongUrl test = new ModifyWrongUrl();
        String jsList = readJson("C:\\Users\\sales" +
                "\\Downloads\\docs\\20181210_1505\\customer-000008.json");
        test.ModifyURI(jsList);
    }

    public void WriteURI2File(String path,String uri){

    }

    public void ModifyURI(String str) throws IOException {
        //将String转成Json对象并设置编码格式
        JSONObject jsonObject = JSONObject.parseObject
                (new String(str.getBytes("utf-8"), "utf-8"));
        //找到json中名为data的数组
        JSONArray data = jsonObject.getJSONArray("data");
        for (int i = 0; i < data.size(); i++) {
            //JSONObject jOb1 = (JSONObject) data.get(i);
            JSONObject jOb1 = data.getJSONObject(i);
            String url = jOb1.getString("主页（网址）");
            if (url==null||
                    url.substring(7).contains("wu")||
                    url.substring(7).contains("无")) continue;
            Pattern pattern=Pattern.compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$");
            Matcher matcher = pattern.matcher(url);
//            while(matcher.find()) {
//                String group = matcher.group();
//                System.out.println(group);
//            }
            // System.out.println(pattern.matcher(string).matches());
            if (!matcher.matches()) {
                //String group = matcher.group();
                url = url.substring(0,7).toLowerCase() == "http://" ? url : "http://" + url;
                jOb1.put("主页（网址)",url);
            }
        }

    }
    /**
     * 将文件中的数据读出并返回String字符串
     * @param srcFile
     * @return
     * @throws IOException
     */
    public static String readJson(String srcFile) throws IOException {
        // 读取文件数据
        StringBuilder builder = new StringBuilder();
        File myFile = new File(srcFile);
        if (!myFile.exists()) {
            return "FileNotFound";
        }
        try {
            int size = 20 * 1024 * 1024;
            FileInputStream fis = new FileInputStream(srcFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, "utf-8");
            BufferedReader in = new BufferedReader(inputStreamReader, size);
            String str;
            while ((str = in.readLine()) != null) {
                builder.append(str);
            }
            in.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
        return builder.toString();
    }
}
