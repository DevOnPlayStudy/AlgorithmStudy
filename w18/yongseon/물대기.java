package w18.yongseon;

import java.util.*;

public class 물대기 {
    static class Edge implements Comparable<Edge> {
        int dest;
        int weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 비용 정보 입력 받기
        int[] wellCost = new int[n + 1]; // 논의 우물 파기 비용
        for (int i = 1; i <= n; i++) {
            wellCost[i] = sc.nextInt();
        }

        // 논들 사이의 연결 비용 입력 받기
        int[][] connectCost = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                connectCost[i][j] = sc.nextInt();
            }
        }

        // 프림 알고리즘을 위한 우선순위 큐 사용
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];
        int totalCost = 0;

        // 가상의 0번 정점에서 각 논으로 우물 파기 비용으로 연결되는 간선 추가
        for (int i = 1; i <= n; i++) {
            pq.offer(new Edge(i, wellCost[i]));
        }

        int count = 0;
        while (!pq.isEmpty() && count < n) {
            Edge current = pq.poll();

            if (visited[current.dest]) {
                continue;
            }

            visited[current.dest] = true;
            totalCost += current.weight;
            count++;

            // 현재 정점과 연결된 다른 논들로의 간선을 추가
            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    pq.offer(new Edge(i, connectCost[current.dest][i]));
                }
            }
        }

        System.out.println(totalCost);
    }
}

