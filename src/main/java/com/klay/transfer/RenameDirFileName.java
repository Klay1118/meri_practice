package com.klay.transfer;

import com.alibaba.fastjson.JSONObject;
import com.klay.utils.ReadJsonFile;

import java.io.File;
import java.io.IOException;

import static com.klay.utils.ReadJsonFile.readJson;

public class RenameDirFileName {
    public static void main(String[] args) throws IOException {
      /*  //File file=new File("C:\\Users\\sales\\Downloads\\docs\\20181206_1656\\data\\customer-000173.json");
        String path="C:\\Users\\sales\\Downloads\\docs\\20181206_1656\\data\\customer-000389.json";
       // ReadJsonFile readJsonFile=new ReadJsonFile();
        String json = readJson(path);
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(jsonObject);*/
      /*String path="C:\\Users\\sales\\Downloads\\docs\\20181210_1445";
      File file=new File(path);
      File[] files = file.listFiles();
        for (File f:files) {
            if(f.getName().endsWith(".done")){
                f.renameTo(new File(f.getAbsolutePath()+".json"));
            }else {
                System.out.println("不是done文件");
            }
        }
        System.out.println("重命名成功");*/
        {
            String [] fileName = getFileName("C:\\Users\\sales\\Downloads\\docs\\20181210_1341\\data");//<span style="font-family: Arial, Helvetica, sans-serif;">此处修改为你的本地路径</span>
            for (int i = 0; i < fileName.length; i++) {
                renameFile("C:\\Users\\sales\\Downloads\\docs\\20181210_1341\\data",
                        fileName[i], fileName[i].replaceAll(".down","")+".json");//cx修改为你要修改的文件名格式
            }
        }
    }
    public static String [] getFileName(String path)
    {
        File file = new File(path);
        String [] fileName = file.list();
        return fileName;
    }
    public static void renameFile(String path,String oldname,String newname){
        if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名
            File oldfile=new File(path+"\\"+oldname);
            File newfile=new File(path+"\\"+newname);
            if(!oldfile.exists()){
                return;//重命名文件不存在
            }
            if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名
                System.out.println(newname+"已经存在！");
            else{
                oldfile.renameTo(newfile);
            }
        }else{
            System.out.println("新文件名和旧文件名相同...");
        }
    }
}


