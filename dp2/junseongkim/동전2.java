package dp2.junseongkim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/2294
 * <p>
 * coins = 1 , 5, 12
 * <p>
 * coin =1
 * 0  1   2   3   4   5   6   7   8   9   10  11  12  13  14  15
 * v -1   1   2   3   4   1   2   3   4   5   2   3   1   2   3   3
 * <p>
 * dp[i] = i를 만들기 위한 최소 동전 개수
 */
public class 동전2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] coins, dp;

    public static void main(String[] args) throws IOException {
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int k = Integer.parseInt(split[1]);

        coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        dp = new int[k + 1];
        Arrays.fill(dp, k + 1); // 초기화 : dp 배열을 큰 값을 초기화
        dp[0] = 0; // 0원인 경우의 수: 1번 (아무것도 사용하지 않음)

        /**
         * 점화식
         *
         * dp[i] = min(dp[i], dp[i-coin] + 1)
         * dp[1] = min(dp[1], dp[1-1] + 1) = min(dp[1], dp[0] + 1)
         *
         */
        for (int coin : coins) {
            for (int i = coin; i <= k; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        if (dp[k] == k + 1) {
            System.out.println(-1);
        } else {
            System.out.println(dp[k]);
        }

    }
}
