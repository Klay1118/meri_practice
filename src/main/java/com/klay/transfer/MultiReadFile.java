package com.klay.transfer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MultiReadFile {


        public static class ThreadRead implements Runnable {
            private String fileName;
            private  int num;
            private ArrayList<String> list;

            public ThreadRead(String fileName, int num, ArrayList<String> list) {
                this.fileName = fileName;
                this.num = num;
                this.list = list;
            }

            @Override
            public void run() {
                String filepath = fileName.replace("{PAGE}", num + "");
                File file = new File(filepath);
                if (!file.exists()){
                    return ;
                }
                //或者
//        if (num > 你的最大值){
//            return null;
//        }

                try {
                    BufferedReader bf = new BufferedReader(new FileReader(file));
                    String content = "";
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        content = bf.readLine();
                        if (content == null) {
                            break;
                        }
                        sb.append(content.trim());
                    }
                    bf.close();
                    System.out.println(sb.toString());
                    list.add(sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        public static void main(String[] args) throws IOException {
            String file = "C:\\Users\\sales\\Downloads\\docs\\20181206_1422\\data\\customer-{PAGE}.txt";
            int fileCount = 5;
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i <= fileCount;i++) {
                Thread th1 = new Thread((Runnable) new ThreadRead(file, i,list));
                th1.start();
            }
            for (String  s : list){
                System.out.println(s);
            }


        }
    }


