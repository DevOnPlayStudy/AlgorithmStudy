package w18.yongseon;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 네트워크연결 {

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

    public static List<List<Edge>> graph = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 컴퓨터 개수
        int n = sc.nextInt();
        // 연결할 수 있는 선의 개수
        int m = sc.nextInt();

        for (int i = 0; i <= n ; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 정보 입력
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        System.out.println(prim(n));
    }

    public static int prim(int n) {
        boolean[] visited = new boolean[n+1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        int totalWeight = 0;
        pq.offer(new Edge(1, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (visited[current.dest]) {
                continue;
            }

            visited[current.dest] = true;
            totalWeight = current.weight;

            for (Edge edge : graph.get(current.dest)) {
                if (!visited[edge.dest]) {
                    pq.offer(edge);
                }
            }
        }

        return totalWeight;
    }
}
