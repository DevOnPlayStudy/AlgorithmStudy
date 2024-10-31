package w17.yongseon;

import java.util.*;

public class 비밀모임 {
    static List<List<Node>> graph = new ArrayList<>();
    static int[] friends;

    static class Node implements Comparable<Node>{
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex=vertex;
            this.weight=weight;
        }

        @Override
        public int compareTo(Node other) {
            if (this.weight == other.weight) {
                return Integer.compare(this.vertex, other.vertex);
            }
            return Integer.compare(this.weight, other.weight);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();

        for(int i=0; i < testCase; i++) {
            setCase(sc);
            System.out.println(findMeetingRoom());
        }
    }

    private static int findMeetingRoom() {
        int[][] allDistances = new int[friends.length][];

        // 각 친구의 위치에서 다익스트라 실행
        for (int i = 0; i < friends.length; i++) {
            allDistances[i] = dijkstra(friends[i]);
        }

        int meetCost = Integer.MAX_VALUE;
        int meetPoint = -1;

        for (int room = 1; room < graph.size(); room++) {
            int distanceSum = 0;
            boolean valid = true;

            for (int i = 0; i < friends.length; i++) {
                if (allDistances[i][room] == Integer.MAX_VALUE) {
                    valid = false;
                    break;
                }
                distanceSum += allDistances[i][room];
            }

            if (valid && distanceSum < meetCost) {
                meetCost = distanceSum;
                meetPoint = room;
            } else if (valid && distanceSum == meetCost) {
                meetPoint = Math.min(meetPoint, room);
            }
        }

        return meetPoint;
    }

    private static void setCase(Scanner sc) {
        graph = new ArrayList<>();
        int rooms = sc.nextInt();
        int edges = sc.nextInt();

        for (int i = 0; i <= rooms; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int weight = sc.nextInt();
            graph.get(a).add(new Node(b, weight));
            graph.get(b).add(new Node(a, weight));
        }

        friends = new int[sc.nextInt()];

        for (int i = 0; i < friends.length; i++) {
            friends[i] = sc.nextInt();
        }
    }

    private static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] minDistances = new int[graph.size()];
        Arrays.fill(minDistances, Integer.MAX_VALUE);
        minDistances[start] = 0; // 시작 노드의 거리를 0으로 설정

        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentVertex = current.vertex;
            int currentWeight = current.weight;

            if (currentWeight > minDistances[currentVertex]) {
                continue;
            }

            for (Node node : graph.get(currentVertex)) {
                if (minDistances[currentVertex] != Integer.MAX_VALUE) {
                    int newDist = minDistances[currentVertex] + node.weight;

                    if (newDist < minDistances[node.vertex]) {
                        minDistances[node.vertex] = newDist;
                        pq.offer(new Node(node.vertex, newDist));
                    }
                }
            }
        }

        return minDistances;
    }
}

