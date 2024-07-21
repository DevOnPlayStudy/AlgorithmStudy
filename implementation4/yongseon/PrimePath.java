package implementation4.yongseon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PrimePath {
    static final int MAX = 10000;
    static boolean[] isPrime = new boolean[MAX];
    static List<Integer>[] graph = new ArrayList[MAX];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        setIsPrime();
        setGraph();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int[] startEnd = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            int answer = bfs(startEnd[0], startEnd[1]);

            if(answer == -1) {
                System.out.println("Impossible");
            } else {
                System.out.println(answer);
            }
        }


    }

    // 에라토스테네스의 체를 활용하여 소수들만 구함
    private static void setIsPrime() {
        Arrays.fill(isPrime, true);

        // 0, 1은 소수이기 때문에 제외
        isPrime[0] = isPrime[1] = false;

        // 2 ~ 10000까지 각 배수들을 소수에 제외합니다.
        for (int i = 2; i < MAX; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    // 비밀번호를 변경하면서 나올 수 있는 경우의 소수를 그래프로 그현
    private static void setGraph() {
        // 1000~9999 까지 모든 경우의 수를 반복
        for (int i = 1000; i < MAX; i++) {
            // 소수가 아닌 경우는 패스
            if(isPrime[i]){
                graph[i] = new ArrayList<>();
                char[] nums = Integer.toString(i).toCharArray();
                // 첫번째 자리 수부터 네번째 자리수까지 0~9까지 대입해보면서 소수가 있을시에는 하위 노드에 추가
                // 비밀번호를 한 번에 한 자리 밖에 못 바꾸는 조건이 있음
                for (int j = 0; j < 4; j++) {
                    char num = nums[j];
                    for (char chr = '0'; chr <= '9'; chr++) {
                        if(num != chr) {
                            nums[j] = chr;

                            int originNums = Integer.parseInt(new String(nums));

                            // 1000보다 크고 소수인 경우 하위 노드에 추가합니다.
                            if (originNums >= 1000 && isPrime[originNums]) {
                                graph[i].add(originNums);
                            }
                        }
                    }
                    nums[j] = num;
                }
            }
        }
    }

    private static int bfs(int start, int end) {
        // 방문 여부를 체크하는 배열 생성
        boolean[] visited = new boolean[MAX];
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{start, 0});
        visited[start] = true;

        // 그래프에 있는 노드들을 하나씩 탐색하면서 원하는 값이 존재하는지 검색
        // 이미 방문했다면 다음 노드를 탐색
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int num = current[0];
            int cost = current[1];

            if (num == end) {
                return cost;
            }

            for (int next : graph[num]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(new int[]{next, cost + 1});
                }
            }
        }

        return -1;
    }
}
