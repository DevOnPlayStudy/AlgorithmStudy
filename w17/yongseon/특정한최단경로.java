package w17.yongseon;

import java.util.*;

public class 특정한최단경로 {

    static class Node implements Comparable<Node> {
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {

            return Integer.compare(this.weight, o.weight);
        }
    }
    static List<Node>[] graph;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int e = sc.nextInt();

        graph = new ArrayList[n+1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        int v1 = sc.nextInt();
        int v2 = sc.nextInt();

        int v1First = sum(1, v1, v2, n);
        int v2First = sum(1, v2, v1, n);

        if (v1First == Integer.MAX_VALUE && v2First == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(v1First, v2First));
        }
    }

    private static int sum(int a, int b, int c, int d) {
        int aTob = dijkstra(a, b);
        if (aTob == Integer.MAX_VALUE) return Integer.MAX_VALUE;

        int bToc = dijkstra(b, c);
        if (bToc == Integer.MAX_VALUE) return Integer.MAX_VALUE;

        int cTod = dijkstra(c, d);
        if (cTod == Integer.MAX_VALUE) return Integer.MAX_VALUE;

        return aTob + bToc + cTod;
    }

    private static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentVertex = current.vertex;
            int currentWeight = current.weight;

            if (currentVertex == end) {
                return currentWeight;
            }

            if (currentWeight > distances[currentVertex]) {
                continue;
            }

            for (Node node : graph[currentVertex]) {
                int newDist = distances[currentVertex] + node.weight;
                if (newDist < distances[node.vertex]) {
                    distances[node.vertex] = newDist;
                    pq.offer(new Node(node.vertex, newDist));
                }
            }
        }

        return Integer.MAX_VALUE;
    }
}
