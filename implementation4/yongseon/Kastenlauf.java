package implementation4.yongseon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Kastenlauf {
    public static class Point {
        private int x;
        private int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int distance(Point other) {
            return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 시뮬레이션 수
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            // 편의점 개수
            int n = Integer.parseInt(br.readLine());

            // 편의점, 집, 페스티벌 위치를 담을 배열
            Point[] points = new Point[n+2];

            // 편의점, 집, 페스티벌 방문 여부를 담을 배열
            boolean[] visited = new boolean[n+2];

            // 편의점, 집, 페스티벌 위치 저장
            for (int j = 0; j < n+2; j++) {
                int[] xy = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();

                points[j] = new Point(xy[0], xy[1]);
            }

            if(bfs(points, visited, n)) {
                System.out.println("happy");
            } else {
                System.out.println("sad");
            }
        }
    }


    private static boolean bfs(Point[] points, boolean[] visited, int n) {
        // 집에서 출발 시작
        Queue<Point> queue = new LinkedList<>();
        queue.add(points[0]);
        visited[0] = true;

        // 모든 편의점의 위치에서 발생할 수 있는 경우의 수를 구해야되기 때문에 아래와 같이 BFS를 탐색해야한다.
        while (!queue.isEmpty()) {
            Point currentPoint = queue.poll();

            // 페스티벌이 남을 때까지 반복한다.
            for (int i = 0; i < n+2; i++) {
                // 다음 위치가 1000 이하면 큐에 담는다.
                if (!visited[i] && currentPoint.distance(points[i]) <= 1000) {
                    if (i == n + 1) {
                        return true;
                    }
                    queue.add(points[i]);
                    visited[i] = true;
                }
            }
        }

        return false;
    }
}
