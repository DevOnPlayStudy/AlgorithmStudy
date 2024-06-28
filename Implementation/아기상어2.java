package Implementation;

import java.io.*;
import java.util.*;

public class 아기상어2 {

    static int[][] map;
    static int N, M;
    static int[] dirRow = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dirColumn = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};
    static boolean[][] visited;
    static Queue<NowIndex> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);

        map = new int[N][M];
        // 맵 정보 입력받기
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int totalMax = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) continue;
                int maxBfs = bfs(i, j);
                if (maxBfs > totalMax) {
                    totalMax = maxBfs;
                }
            }
        }
        System.out.println(totalMax);
    }

    static class NowIndex {
        private int row;
        private int column;
        private int distance;

        public NowIndex(int row, int column, int distance) {
            this.row = row;
            this.column = column;
            this.distance = distance;
        }
    }

    static int bfs(int row, int column) {
        visited = new boolean[N][M];
        queue.offer(new NowIndex(row, column, 0));
        visited[row][column] = true;

        while (!queue.isEmpty()) {
            NowIndex poll = queue.poll();
            int nowRow = poll.row;
            int nowColumn = poll.column;
            int nowDistance = poll.distance;

            if (map[nowRow][nowColumn] == 1) {
                queue.clear();
                return nowDistance;
            }

            for (int i = 0; i < dirRow.length; i++) {
                int nextRow = nowRow + dirRow[i];
                int nextColumn = nowColumn + dirColumn[i];

                if (validatate(nextRow, nextColumn) && !visited[nextRow][nextColumn]) {
                    queue.offer(new NowIndex(nextRow, nextColumn, nowDistance + 1));
                    visited[nextRow][nextColumn] = true;
                }
            }
        }
        return 0;
    }

    static boolean validatate(int nextRow, int nextColmn) {
        return nextRow >= 0 && nextColmn >= 0 && nextRow < map.length && nextColmn < map[0].length;
    }

}
