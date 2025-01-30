package Jan30th;

import java.io.*;

public class A와B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder startStr = new StringBuilder(br.readLine());
        StringBuilder targetStr = new StringBuilder(br.readLine());

        // B -> ABBA
        // ABBA -> B
        while (startStr.length() < targetStr.length()) {
            if (targetStr.charAt(targetStr.length() - 1) == 'A') { // A인 경우 제거
                targetStr.deleteCharAt(targetStr.length() - 1);
            }else if(targetStr.charAt(targetStr.length() - 1) == 'B'){
                targetStr.deleteCharAt(targetStr.length() - 1);
                targetStr.reverse();
            }
        }

        System.out.println(targetStr.toString().equals(startStr.toString()) ? 1 : 0);

    }


}
