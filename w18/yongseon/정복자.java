package w18.yongseon;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 정복자 {
    private static List<List<Edge>> graph = new ArrayList<>();

    public static class Edge implements Comparable<Edge> {
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
        // 도시의 개수
        int n = sc.nextInt();
        
        // 도로의 개수
        int m = sc.nextInt();
        
        // 정복할 때마다 증가하는 비용
        int t = sc.nextInt();
        
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        System.out.println(prim(n, t));
    }

    private static int prim(int n, int t) {
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int totalWeight = 0;
        int conqueredCities = 0;

        pq.offer(new Edge(1, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (visited[current.dest]) {
                continue;
            }

            visited[current.dest] = true;

            totalWeight += current.weight;

            // 도시를 하나 정복할 때마다 정복한 도시의 수를 증가
            conqueredCities++;

            // 도시를 정복할 때마다 모든 도로의 비용이 t만큼 증가함
            if (conqueredCities > 2) {  // 처음 시작 시에는 비용 증가 없음
                totalWeight += t*(conqueredCities-2);
            }

            for (Edge edge : graph.get(current.dest)) {
                if (!visited[edge.dest]) {
                    pq.offer(edge);
                }
            }
        }

        return totalWeight;
    }
}
