package com.klay.utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadJsonFile {
    final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 读取目录下的json文件，返回String字符串
     * @param FileDir
     * @return
     * @throws IOException
     */
    public static String readJsonDir(String FileDir) throws IOException {
        StringBuffer stringBuffer=new StringBuffer();
        File f = new File(FileDir);
        // 文件总数
        final List<File> filePathsList = new ArrayList<File>();
        File[] filePaths = f.listFiles();
        for (File s : filePaths) {
            filePathsList.add(s);
        }

        System.out.println("-------------文件读任务开始时间：" + sdf.format(new Date()));

        for (int i=0;i<filePathsList.size();i++){
            File file = filePathsList.get(i);
            String str = readJson(file.getAbsolutePath());
            stringBuffer.append(str);
        }
        //System.out.println(stringBuffer);
        return  stringBuffer.toString();
    }

    public static String readJson(String srcFile) throws IOException {
        // 读取文件数据
        StringBuilder builder = new StringBuilder();
        File myFile = new File(srcFile);
        if (!myFile.exists()) {
            System.err.println("找不到" + srcFile);
            return "FileNotFound";
        }
        try {
            int size=20*1024*1024;
            FileInputStream fis = new FileInputStream(srcFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, "utf-8");
            BufferedReader in  = new BufferedReader(inputStreamReader,size);
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
