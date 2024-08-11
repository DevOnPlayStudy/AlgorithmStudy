package dp.yongseon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ContinuousSum {
    private static int[]  continuousArr;
    private static int[] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());

        continuousArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp = new int[n];

        int max = continuousArr[0];
        dp[0] = continuousArr[0];

        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i-1]+continuousArr[i], continuousArr[i]);
            max = Math.max(dp[i], max);
        }

        System.out.println(max);
    }
}
