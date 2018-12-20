package com.klay.utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ReadJsonFileTest {

    public static void main(String[] args) throws IOException {
        String fileDir = "C:\\Users\\sales\\Downloads\\docs\\20181206_1252\\data\\customer-000000.json";

       // String str = readJsonDir(fileDir);
        final String str = readJson(fileDir);
        System.out.println(str);
    }

    final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String readJsonDir(String FileDir) throws IOException {
        StringBuffer stringBuffer=new StringBuffer();
        File f = new File(FileDir);
        // 文件总数
        final List<File> filePathsList = new ArrayList<File>();
        File[] filePaths = f.listFiles();
        for (File s : filePaths) {
            filePathsList.add(s);
            System.out.println(s.getName());
        }

        System.out.println("-------------文件读任务开始时间：" + sdf.format(new Date()));

        for (int i=0;i<filePathsList.size();i++){
            File file = filePathsList.get(i);
            String str = readJson(file.getAbsolutePath());
            System.out.println(str);
            stringBuffer.append(str);
        }
        System.out.println(stringBuffer.toString());
        return  stringBuffer.toString();
    }

    private static String readJson(String srcFile) throws IOException {
        // 读取文件数据
        StringBuffer strbuffer = new StringBuffer();
        File myFile = new File(srcFile);
        if (!myFile.exists()) {
            System.err.println("找不到" + srcFile);
            return "FileNotFound";
        }
        try {
            FileInputStream fis = new FileInputStream(srcFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, "GBK");
            BufferedReader in  = new BufferedReader(inputStreamReader);
            String str;
            while ((str = in.readLine()) != null) {
                strbuffer.append(str);
            }
            in.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
        return strbuffer.toString();
    }
}
