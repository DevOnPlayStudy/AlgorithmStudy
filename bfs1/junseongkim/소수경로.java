package bfs1.junseongkim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1963
public class 소수경로 {

    static boolean[] isPrime = new boolean[10000];
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        /// 소수를 찾기 위한 환경 조성
        Arrays.fill(isPrime, true); // 소수의 진위 여부를 확인하기위해 isPrime 배열의 모든 값을 true로 초기화
        isPrime[1] = false; // 1은 소수가 아니라서 false 처리

        for (int i = 2; i < 10000; i++) {
            if (isPrime[i]) {// 소수가 true라면
                for (int j = 2; i * j < 10000; j++) {
                    isPrime[i * j] = false; // 2, 3, 4, 5 의 배수들은 모두 소수가 아니기에 false 처리
                }
            }
        }

        // 테스크 케이스 개수
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) { // 테스트케이스가 0일때까지 반복
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startNum = Integer.parseInt(st.nextToken());
            int endNum = Integer.parseInt(st.nextToken());

            visited = new boolean[10000];

            int result = bfs(startNum, endNum);

            if (result == -1) {
                sb.append("Impossible\n");
            } else {
                sb.append(result).append("\n");
            }
        }
        System.out.println(sb);

    }

    static int bfs(int startNum, int endNum) {
        Queue<Numbers> q = new LinkedList<>();
        q.offer(new Numbers(startNum, 0));
        visited[startNum] = true;

        while (!q.isEmpty()) {
            Numbers currentNum = q.poll();

            if (currentNum.num == endNum) return currentNum.cnt; // 우선순위큐로 가장 먼저 도착한 cnt가 최소

            // 천의 자리수 변경
            int temp = currentNum.num % 1000; // 1033 -> 33
            for (int i = 1; i < 10; i++) { // 천의자리여서 0은 불가 1 ~ 9 까지
                int nextNum = i * 1000 + temp; // 1033 -> 2033 -> 3033 -> ... -> 9033

                if (nextNum != currentNum.num) { // 다음 숫자가 현재 숫자와 같아선 안된다.
                    if (isPrime[nextNum]) { // 다음 숫자는 무조건 소수여야한다.
                        if (!visited[nextNum]) { // 다음 숫자는 한번만 선택되어야하기에 방문처리가 false 여야함
                            q.offer(new Numbers(nextNum, currentNum.cnt + 1)); //
                            visited[nextNum] = true;
                        }
                    }
                }
            }

            // 백의 자리수 변경
            int chun = (currentNum.num / 1000) * 1000;  // 1033 -> 1000
            temp = currentNum.num % 100; // 1033 -> 33
            for (int i = 0; i < 10; i++) { // 백의 자리라 0부터 가능 0 ~ 9 까지 수행
                int nextNum = chun + i * 100 + temp; // 1033 -> 1133 -> 1233 ... -> 1933

                if (nextNum != currentNum.num) { // 다음 숫자가 현재 숫자와 같아선 안된다.
                    if (isPrime[nextNum]) { // 다음 숫자는 무조건 소수여야한다.
                        if (!visited[nextNum]) { // 다음 숫자는 한번만 선택되어야하기에 방문처리가 false 여야함
                            q.offer(new Numbers(nextNum, currentNum.cnt + 1)); //
                            visited[nextNum] = true;
                        }
                    }
                }
            }

            // 십의 자리 수 변경
            int chunBaek = (currentNum.num / 100) * 100; // 1033 -> 1000
            temp = currentNum.num % 10;
            for (int i = 0; i < 10; i++) {
                int nextNum = chunBaek + i * 10 + temp; // 1033 -> 1003 -> 1013 -> ... 1093

                if (nextNum != currentNum.num) { // 다음 숫자가 현재 숫자와 같아선 안된다.
                    if (isPrime[nextNum]) { // 다음 숫자는 무조건 소수여야한다.
                        if (!visited[nextNum]) { // 다음 숫자는 한번만 선택되어야하기에 방문처리가 false 여야함
                            q.offer(new Numbers(nextNum, currentNum.cnt + 1)); //
                            visited[nextNum] = true;
                        }
                    }
                }
            }

            // 일의 자리 수 변경
            int chunBaekSheep = (currentNum.num / 10) * 10;

            for (int i = 0; i < 10; i++) {
                int nextNum = chunBaekSheep + i;

                if (nextNum != currentNum.num) { // 다음 숫자가 현재 숫자와 같아선 안된다.
                    if (isPrime[nextNum]) { // 다음 숫자는 무조건 소수여야한다.
                        if (!visited[nextNum]) { // 다음 숫자는 한번만 선택되어야하기에 방문처리가 false 여야함
                            q.offer(new Numbers(nextNum, currentNum.cnt + 1)); //
                            visited[nextNum] = true;
                        }
                    }
                }
            }
        }

        return -1; // 위 while 문 수행 시 return 되지 않으면 마지막에 -1 반환
    }

    static class Numbers {
        int num; // 현재 숫자와 다음 숫자를 정의하기위해 사용
        int cnt; // 다음 숫자를 추가할때 1씩 횟수를 추가해주는 용도

        public Numbers(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

    }

}
