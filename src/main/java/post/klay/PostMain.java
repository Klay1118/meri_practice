package post.klay;

import java.io.File;

public class PostMain {
    public static void main(String[] args) {
        //String path="C:\\Users\\sales\\Downloads\\docs\\20181210_1505";
        String url = "http://rest.youngfey.com:8080/netsuite/api?script=158&deploy=5&user=dai";
        // int threadsNum=10;
        // File folder=new File(path);
        SendPost sendPost = new SendPost();

        // folder.listFiles();


    }

    public boolean findFile(int idx) {
        String path = "C:\\Users\\sales\\Downloads\\docs\\20181210_1505";
        File folder = new File(path);
        int threadsNum = 10;
        File[] files = folder.listFiles();

        for (File f : files) {
            String substring = f.getName().substring(9, 15);
            int endNum = Integer.parseInt(substring);
//            if (endNum % threadsNum == idx)
//                return true;
            if (f.getName().endsWith(".json") && (endNum % threadsNum == threadsNum))
                return true;
        }
        return true;

    }


}

/*for (File file : folder.listFiles()) {
            boolean b = file.getName().endsWith(".json");
            String substring = file.getName().substring(9, 15);
                int endNum = Integer.parseInt(substring);
                if (endNum % threadsNum == idx)
                    return true;
        }*/
