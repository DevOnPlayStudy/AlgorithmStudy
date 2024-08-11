package bfs2.yongseon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MonkeyHorseGame {
    private static int[][] map;
    private static final Queue<Point> queue = new LinkedList<>();
    private static int[] dx = new int[] {0, 0, 1, -1};
    private static int[] dy = new int[] {1, -1, 0, 0};
    private static int[] jx = new int[] {2, 2, 1, 1, -2, -2, -1, -1};
    private static int[] jy = new int[] {1, -1, 2, -2, 1, -1, 2, -2};
    private static boolean[][][] visited;

    public static class Point {
        private int x, y, k, cost;

        public Point(int x, int y, int k, int cost) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());
        int[] wh = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        setMap(br, wh[0], wh[1], k);
        System.out.println(bfs(k, wh[0], wh[1]));
    }

    private static void setMap(BufferedReader br, int w, int h, int k) throws IOException {
        map = new int[h][w];
        visited = new boolean[h][w][k + 1];

        for (int i = 0; i < h; i++) {
            int[] mapCols = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < mapCols.length; j++) {
                map[i][j] = mapCols[j];
            }
        }
    }

    private static int bfs(int k, int w, int h) {
        queue.add(new Point(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.x == w - 1 && current.y == h - 1) {
                return current.cost;
            }

            // 일반 이동
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (validation(nx, ny, h, w) && !visited[ny][nx][current.k]) {
                    visited[ny][nx][current.k] = true;
                    queue.add(new Point(nx, ny, current.k, current.cost + 1));
                }
            }

            // 말 이동
            if (current.k < k) {
                for (int i = 0; i < 8; i++) {
                    int nx = current.x + jx[i];
                    int ny = current.y + jy[i];

                    if (validation(nx, ny, h, w) && !visited[ny][nx][current.k + 1]) {
                        visited[ny][nx][current.k + 1] = true;
                        queue.add(new Point(nx, ny, current.k + 1, current.cost + 1));
                    }
                }
            }
        }

        return -1; // 도착할 수 없는 경우
    }

    private static boolean validation(int x, int y, int h, int w) {
        return x >= 0 && x < w && y >= 0 && y < h && map[y][x] != 1;
    }
}
