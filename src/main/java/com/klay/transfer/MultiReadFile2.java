package com.klay.transfer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.concurrent.*;

public class MultiReadFile2 implements Callable<String> {
    private String filepath;

    public MultiReadFile2(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public String call() throws Exception {
        String con = "";
        File file = new File(filepath);
        if (!file.exists()) {
            return null;
        }
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(file));
            String content = "";
            StringBuilder sb = new StringBuilder();
            while (content != null) {
                content = bf.readLine();
                if (content == null) {
                    break;
                }
                sb.append(content);
            }

            bf.close();
            con = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();
        String file = "C:\\Users\\sales\\Downloads\\docs\\20181206_1656\\data\\customer-{PAGE}.json";
        int fileCount = 12328;
        for (int i = 0; i <= fileCount; i++) {
            if (i >= 10) {
                if (i < 100 && i >= 10) {
                    String filepath = file.replace("{PAGE}", "0000" + i);
                    results.add(exec.submit(new MultiReadFile2(filepath)));
                } else if (i < 1000 && i >= 100) {
                    String filepath = file.replace("{PAGE}", "000" + i);
                    results.add(exec.submit(new MultiReadFile2(filepath)));
                } else if (i < 10000 && i >= 1000) {
                    String filepath = file.replace("{PAGE}", "00" + i);
                    results.add(exec.submit(new MultiReadFile2(filepath)));
                } else if (i < 100000 && i >= 10000) {
                    String filepath = file.replace("{PAGE}", "0" + i);
                    results.add(exec.submit(new MultiReadFile2(filepath)));
                }
            } else {
                String filepath = file.replace("{PAGE}", "00000" + i);
                results.add(exec.submit(new MultiReadFile2(filepath)));
            }
        }
        for (Future<String> fs : results) {
            String content = fs.get();
           // System.out.println(content);
        }
        exec.shutdown();
    }
}
