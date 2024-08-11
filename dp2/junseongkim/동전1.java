package dp2.junseongkim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/2293
 * <p>
 * coin = 1원
 * dp[0] = 1
 * dp[1] = dp[1-1] = dp[0] = 1
 * dp[2] = dp[2-1] = dp[1] = 1
 * dp[3] = dp[3-1] = dp[2] = 1
 * dp[4] = dp[4-1] = dp[3] = 1
 * ...
 * dp[10] = dp[10-1] = dp[9] = 1
 * <p>
 * coin = 2원
 * dp[0] = 1
 * dp[1] = 1
 * dp[2] = dp[2-2] = dp[0] = 1 => 2
 * dp[3] = dp[3-2] = dp[1] = 1 => 2
 * dp[4] = dp[4-2] = dp[2] =
 */
public class 동전1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] coins;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int k = Integer.parseInt(split[1]);

        coins = new int[n];
        dp = new int[k + 1];

        // 코인 가치 입렵받기
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 1; // 0원을 만드는 경우의 수는 아무것도 사용하지 않는 경우 1가지
        for (int coin : coins) {
            for (int i = coin; i <= k; i++) {
                dp[i] = dp[i] + dp[i - coin];
            }
        }
        System.out.println(dp[k]);
    }
}
