package dp1.junseongkim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_연속합_1914 {
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp = new int[n];

        dp[0] = array[0];

        int result = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(array[i], array[i] + dp[i - 1]);
            result = Math.max(dp[i], result);
        }

        System.out.println(result);


    }
}
