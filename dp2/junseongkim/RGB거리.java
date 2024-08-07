package dp2.junseongkim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/1149
 * <p>
 * 1번 집의 색은 2번 집의 색과 같지 않아야한다.
 * => 시작 집은 연달아서 같은 색이면 안됨 (R, R ...) X |  (R G ...) O
 * <p>
 * N번 집의 색은 N-1번 집의 색과 같지 않아야한다.
 * => 마지막도 연달아 같은 색이면 안됨 (.. R R) X | (..  R G) O
 * <p>
 * i(2<= i <= N-1)번 집의 색은 i-1번, i+1번 집의 색과 같이 않아야함.
 * => 중간 집들도 연달아 같은 색이면 안됨( ... R R G ...) X | (... G R G ... ) O | (... G R R ...) X
 * <p>
 * 1. 입력
 * 1.1 집의 수 입력받음(2 <= N <= 1000)
 * 1.2 N 개의 줄에 각 집을 R, G, B로 칠하는 비용이 1번 집부터 한줄에 하나씩 주어짐.
 * 1.3 집을 칠하는 비용은 1_000 보다 작거나 같음
 * <p>
 * 2. 풀이
 * 2.1 dp = new int[n+1][3]
 * 2.1.1 dp[i][R] 의미 : arr[i][R] 의 값에 이전 행 arr[i-1][R]의 제외하고 가장 작은 값을 더함 Math.min(dp[i-1][G], dp[i-1][B])
 * 2.1.2 dp[i][G] 의미 : arr[i][R] 의 값에 이전 행 arr[i-1][G]의 제외하고 가장 작은 값을 더함 Math.min(dp[i-1][G], dp[i-1][B])
 * 2.1.3 dp[i][B] 의미 : arr[i][R] 의 값에 이전 행 arr[i-1][B]의 제외하고 가장 작은 값을 더함 Math.min(dp[i-1][G], dp[i-1][B])
 * 2.2 dp의 마지막 행의 각 열값에서 최솟값을 구한다.
 * <p>
 * 3. 출력
 * 3.1 dp[n-1]행의 최솟값으 같는 열 출력
 */
public class RGB거리 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] arr;
    static int[][] dp;
    static int R = 0, G = 1, B = 2;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        arr = new int[n][3];
        dp = new int[n][3];

        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dp[0] = arr[0];

        for (int i = 1; i < n; i++) {
            dp[i][R] = arr[i][R] + Math.min(dp[i - 1][G], dp[i - 1][B]);
            dp[i][G] = arr[i][G] + Math.min(dp[i - 1][R], dp[i - 1][B]);
            dp[i][B] = arr[i][B] + Math.min(dp[i - 1][R], dp[i - 1][G]);
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if (min > dp[n - 1][i]) {
                min = dp[n - 1][i];
            }
        }

        System.out.println(min);
    }
}
