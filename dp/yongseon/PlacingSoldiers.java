package dp.yongseon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PlacingSoldiers {
    private static int[] soldiers;
    private static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        soldiers = new int[n];

        dp = new int[n+1];

        soldiers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.fill(dp, 1);

        int answer = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (soldiers[j] > soldiers[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }

            answer = Math.max(answer, dp[i]);
        }

        System.out.println(n-answer);
    }
}
