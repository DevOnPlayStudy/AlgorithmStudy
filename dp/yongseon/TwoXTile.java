package dp.yongseon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TwoXTile {
    private static int[] d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if (n > 3) {
            d = new int[n+1];
            d[1] = 1;
            d[2] = 2;
            d[3] = 3;


            for (int i = 4; i < d.length; i++) {
                d[i] = (d[i-1] + d[i-2])%10007;
            }

            System.out.println(d[n]);
        } else {
            System.out.println(n);
        }
    }
}
