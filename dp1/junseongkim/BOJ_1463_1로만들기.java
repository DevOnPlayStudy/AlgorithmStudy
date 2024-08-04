package dp1.junseongkim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1. DP Top-down 방식으로 풀기 => 잘모르겠음..
 * 2. DP Bottom-up 방식은 이해됨
 */
public class BOJ_1463_1로만들기 {
    static int dp[]; // 메모이제이션 역할

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        //topDown(N);
        bottomUp(N);
    }

    static int topDown(int number) {
        if (number == 1) {
            return 0; // 숫자가 0이면 종료;
        }
        if (dp[number] > 0) {
            return dp[number];
        }

        dp[number] = topDown(number - 1) + 1; // dp[1] = 1 | dp[2] = 2 ... dp[10] = 10

        if (number % 3 == 0) { // 3으로 나눠지면
            dp[number] = Math.min(dp[number], topDown(number / 3) + 1);

        }
        if (number % 2 == 0) {
            dp[number] = Math.min(dp[number], topDown(number / 2) + 1);
            // dp[10] = Math.min(10, 5 + 1) => 6
        }
        return dp[number];

    }

    /**
     * 규칙성
     * number = 2 인경우 2-1 =1, 2/2 =1 => Math.min(2-1, 2/2) = 1
     * dp[2] = 1
     *
     * number = 3 인경우 3-1 = 2 => 2는 앞에서 이미 횟수를 가지고있음 dp[2] = 1
     * dp[3] = dp[2] + 1 = 2;
     *
     * number = 4 인경우 4 /2 = 2 => dp[2] = 1 || 4-1 = 3 => dp[3] = 2
     * dp[4] = Math.min(dp[2] + 1, dp[3] + 1) => 2
     *
     * dp[5] = Math.min(dp[4] + 1, dp[3] + 1 + 1) => 3
     * dp[6] = Math.min(dp[5] + 1, dp[2] + 1, dp[3] + 1) = Math.min(4, 2, 3) = 2
     *
     * dp[7] = Math.min(dp[6] + 1, dp[5] +1 +1) = Math.min(3, 5) = 3
     *
     * dp[8] = Math.min(dp[4] + 1, dp[7] +1) = Math.min(4, 4) = 4
     * dp[9] = Math.min(dp[3] + 1, dp[8] +1) = Math.min(3, 5) = 3
     *
     * dp[10] = Math.min(dp[5] +1, dp[9] + 1) = Math.min(3, 4) = 3
     */
    static void bottomUp(int number) {
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= number; i++) {
            dp[i] = dp[i - 1] + 1; // 1을 빼는 경우
            if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1); // Math.min(1빼는 경우, 2로 나눈 경우)
            if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1); // Math.min(1빼는 경우, 3으로 나눈 경우)
        }
        System.out.println(dp[number]);
    }

}
