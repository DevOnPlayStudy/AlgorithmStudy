package dp1.junseongkim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * bfs로 풀면안되나? 왜 dp 문제일까?
 *
 */
public class BOJ_점프_1890 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];
        long[][] dp = new long[n][n];

        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 0; j < split.length; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }

        dp[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] > 0) { // 0보다 큰 구간만 수행
                    int jumpDistance = map[i][j]; // 점프할 거리
                    if (jumpDistance > 0) { // 점프할 거리가 0인 경우는 종착지점임. 그래서 0보다 큰경우만 고려
                        if (i + jumpDistance < n) { // nowRow + 점프거리 < n(배열의 크기보단 무조건 작아야함)
                            dp[i + jumpDistance][j] += dp[i][j]; // dp[점프할 row 좌표][열은 그대로] 값은 앞선 움직임 횟수를 더해간다.
                        }
                        if (j + jumpDistance < n) { // nowCol + 점프거리 < n(배열의 크기보단 무조건 작아야함)
                            dp[i][j + jumpDistance] += dp[i][j]; // dp[행은 그대로][점프할 col 좌표] 값은 판선 움직임 횟수를 더해간다.
                        }
                    }
                }
            }
        }
        System.out.println(dp[n - 1][n - 1]);
    }
}
