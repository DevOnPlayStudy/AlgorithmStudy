package dp.yongseon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tile {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        System.out.println(tiling(n));
    }

    private static int tiling(int n) {
        if (n % 2 != 0) {
            return 0;
        }

        int[] dp =  new int[n+1];
        dp[0] = 1;
        dp[2] = 3;

        for (int i = 4; i <= n ; i++) {
            dp[i] = dp[i-2] * 3;
            for (int j = 4; j <=i ; j+=2) {
                dp[i] += dp[i-j] * 2;
            }
        }

        return dp[n];
    }
}

