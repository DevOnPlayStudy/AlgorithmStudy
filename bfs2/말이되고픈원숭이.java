package bfs2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 입력
 * 1-1. 정수 K : 말처럼 행동할수 있는 횟수
 * 1-2. 격자판 가로, 세로 : 가로길이 w, 세로길이 h
 * 1-3. 장애물과 평지 : int[][]
 * <p>
 * 2. 로직
 * 2-1. BFS : Queue 내부 타입은 3차원 배열(nextRow, nextCol, K)
 * 2-2. 다음 위치가 map 바깥인 경우 유효성검사 : validate(int row, int col)
 * 2-3. 4방 탐색 배열 : int[] dirRow, dirCol
 * 2-4. 말처럼 움직이는 배열 : int[] horseRow, horseCol
 * 2-5. BFS로 움직일 때 말처럼 움직인 경우 K를 하나씩 차감
 * <p>
 * 3. 출력
 * 3-1. 원숭이 동작수의 최솟값 | 도착점까지 못갈경우 -1
 */
public class 말이되고픈원숭이 {
    static int[] dirRow = {-1, 1, 0, 0};
    static int[] dirCol = {0, 0, 1, -1};
    static int[] horseRow = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] horseCol = {-2, -1, 1, 2, 2, 1, -1, -2};
    static boolean[][][] visited;

    static int[][] map;
    static int K, W, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        String[] split = br.readLine().split(" ");
        H = Integer.parseInt(split[1]);
        W = Integer.parseInt(split[0]);

        map = new int[H][W];
        visited = new boolean[H][W][K + 1];

        for (int i = 0; i < H; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        // 0,0 시작
        queue.add(new Node(0, 0, K, 0));
        visited[0][0][K] = true; // 시작 위치 방문 처리

        while (!queue.isEmpty()) {
            Node nowNode = queue.poll();
            int nowRow = nowNode.row;
            int nowCol = nowNode.col;
            int nowHorseCnt = nowNode.remainHorseCnt;
            int nowMoveCnt = nowNode.moveCnt;

            // 도착지점 도착했을 경우
            if (nowRow == map.length - 1 && nowCol == map[0].length - 1) {
                return nowMoveCnt;
            }

            // K가 남아있으면 말처럼 이동
            if (nowHorseCnt > 0) {
                for (int i = 0; i < 8; i++) {
                    int nextRow = horseRow[i] + nowRow;
                    int nextCol = horseCol[i] + nowCol;

                    if (validate(nextRow, nextCol)) {
                        if (map[nextRow][nextCol] == 0 && !visited[nextRow][nextCol][nowHorseCnt - 1]) { // 다음 말 처럼 움직일때 방문처리를 위해 -1
                            queue.add(new Node(nextRow, nextCol, nowHorseCnt - 1, nowMoveCnt + 1));
                            visited[nextRow][nextCol][nowHorseCnt - 1] = true;
                        }
                    }
                }
            }
            // 왜 else가 들어가면 안되나요?
            // K가 안남아있으면 4방 탐색
            for (int i = 0; i < 4; i++) {
                int nextRow = dirRow[i] + nowRow;
                int nextCol = dirCol[i] + nowCol;

                if (validate(nextRow, nextCol)) {
                    if (map[nextRow][nextCol] == 0 && !visited[nextRow][nextCol][nowHorseCnt]) { // 벽이 아닐때만
                        queue.add(new Node(nextRow, nextCol, nowHorseCnt, nowMoveCnt + 1));
                        visited[nextRow][nextCol][nowHorseCnt] = true;
                    }
                }
            }
        }

        return -1;
    }

    static class Node {
        int row, col, remainHorseCnt, moveCnt;

        public Node(int row, int col, int remainHorseCnt, int moveCnt) {
            this.row = row;
            this.col = col;
            this.remainHorseCnt = remainHorseCnt;
            this.moveCnt = moveCnt;
        }
    }

    static boolean validate(int row, int col) {
        return row >= 0 && col >= 0 && row < map.length && col < map[0].length;
    }
}
