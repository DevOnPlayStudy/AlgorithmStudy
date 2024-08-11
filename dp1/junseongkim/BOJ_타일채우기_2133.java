package dp1.junseongkim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 경우의수 문제라고해서 1x2, 2x1 로 채울 경우의 두 타일의 합계를 구하는 문제인줄 알고 풀었으나. 틀림....
 *
 */
public class BOJ_타일채우기_2133 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        if (n % 2 != 0) {
            System.out.println(0);
            return;
        }

        int[] dp = new int[n + 1];
        dp[2] = 3;

        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 2] * dp[2];
            for (int j = i - 4; j > 0; j -= 2) {
                dp[i] += dp[j] * 2;
            }
            dp[i] += 2;
        }
        System.out.println(dp[n]);
    }
}
