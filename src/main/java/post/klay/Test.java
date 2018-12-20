package post.klay;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        String s = set_nonce();
        System.out.println(s);
    }
    public static String set_nonce() {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 18; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();

    }


}
