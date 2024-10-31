package w17.yongseon;

import java.util.*;

public class 서강그라운드 {

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
    static int[] items;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int r = sc.nextInt();

        items = new int[n+1];
        graph = new ArrayList[n+1];

        for (int i=1; i<=n; i++) {
            graph[i] = new ArrayList<>();
            items[i] = sc.nextInt();
        }

        for (int i = 0; i < r; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int I = sc.nextInt();

            graph[a].add(new Node(b, I));
            graph[b].add(new Node(a, I));
        }

        int max=0;

        for (int i = 1; i <=n ; i++) {
            max = Math.max(max, dijkstra(i, m));
        }

        System.out.println(max);
    }

    public static int dijkstra(int start, int limit) {
        int totalCount = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentVertex = current.vertex;
            int currentWeight = current.weight;

            if (currentWeight > distances[currentVertex]) {
                continue;
            }

            totalCount += items[currentVertex];

            for (Node node : graph[currentVertex]) {
                if (distances[node.vertex]>distances[currentVertex]+node.weight&&distances[currentVertex]+node.weight<=limit) {
                    distances[node.vertex]= distances[currentVertex]+node.weight;
                    pq.offer(new Node(node.vertex, distances[node.vertex]));
                }
            }
        }

        return totalCount;
    }
}
