package dp.yongseon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OneTwoThree {
    static int[] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(countWays(n));
        }
    }

    public static int countWays(int n) {
        if (n < 0) {
            return 0;
        }

        int[] dp = new int[n + 1];

        // Base cases
        dp[0] = 1; // There is one way to get sum 0, which is using no numbers at all

        // Fill the dp array
        for (int i = 1; i <= n; i++) {
            if (i >= 1) dp[i] += dp[i - 1];
            if (i >= 2) dp[i] += dp[i - 2];
            if (i >= 3) dp[i] += dp[i - 3];
        }

        return dp[n];
    }
}
