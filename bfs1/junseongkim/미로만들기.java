package bfs1.junseongkim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/2665
// 다익스트라 + BFS 문제라고함.
public class 미로만들기 {
    static int[][] map;
    // static boolean[][] visited; // 사용하지않는다
    static int[] dirRow = {-1, 0, 1, 0};
    static int[] dirCol = {0, 1, 0, -1};

    static int[][] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        distance = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split("");
            for (int j = 0; j < split.length; j++) {
                map[i][j] = Integer.parseInt(split[j]);
                distance[i][j] = Integer.MAX_VALUE; // 왜 최대 정수값을 넣어주지?
            }
        }

        bfs();
        System.out.println(distance[N - 1][N - 1]); // 마지막 좌표값을 읽는 이유가 뭘까?

    }


    static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});

        distance[0][0] = 0;

        while (!queue.isEmpty()) {

            int[] poll = queue.poll();
            int nowRow = poll[0];
            int nowCol = poll[1];

            for (int i = 0; i < 4; i++) {
                int nextRow = dirRow[i] + nowRow;
                int nextCol = dirCol[i] + nowCol;

                if (validate(nextRow, nextCol)) {
                    if (distance[nextRow][nextCol] > distance[nowRow][nowCol]) {
                        if (map[nextRow][nextCol] == 1) {
                            distance[nextRow][nextCol] = distance[nowRow][nowCol];
                        } else {
                            distance[nextRow][nextCol] = distance[nowRow][nowCol] + 1; // 왜 1을 더해주지?
                        }
                        queue.add(new int[]{nextRow, nextCol});
                    }
                }

            }
        }
    }

    static boolean validate(int row, int col) {
        return row >= 0 && col >= 0 && row < map.length && col < map[0].length;
    }
}
