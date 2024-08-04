package dp1.junseongkim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/18353
 * <p>
 * 1. 입력
 * 1.1 병사수 N ( 1<= N <= 2000 )
 * 1.2 병사의 전투력 정수 배열
 * <p>
 * 2. 풀이
 * ex) 15 11 4 8 5 2 4 => 15 11 8 5 4 로 내림차순으로 되어야함. 이를 위해 4, 2를 제외시킨것.
 * 2.1 두 값을 비교해 작지않다면 해당 값을 제외 => LinkedList로 정의함
 * 2.2 이때 뺄때마다 cnt + 1 풀이가 아닌듯...
 * 2.3 LIS LDS가 뭔지 모르겠음...
 * <p>
 * <p>
 * 3. 출력
 * 3.1 열외 병사 수
 */
public class BOJ_18353_병사배치하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer> dp = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 15 11 4 8 5 2 4
        for (int i = 0; i < N; i++) {
            dp.add(Integer.parseInt(st.nextToken()));
        }

        int cnt = 0;
        // 다음 인덱스와 다다음 인덱스 비교
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < i + 2; j++) {
                int num1 = dp.get(i);
                int num2 = dp.get(j);

                if (num1 <= num2) {
                    dp.remove(j);
                    cnt++;
                }

            }
        }

        System.out.println(cnt);

    }
}
