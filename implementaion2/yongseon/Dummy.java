package implementaion2.yongseon;

import java.util.*;

public class Dummy {
    static int n; // 보드의 크기
    static int k; // 사과의 개수
    static int[][] board; // 보드
    static List<int[]> snake = new LinkedList<>(); // 뱀의 몸통 위치
    static int l; // 방향 변환 횟수
    static Map<Integer, Character> directions = new HashMap<>(); // 방향 변환 정보
    static int[] dx = {0, 1, 0, -1}; // 오른쪽, 아래, 왼쪽, 위
    static int[] dy = {1, 0, -1, 0};
    static int direction = 0; // 초기 방향 오른쪽

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        // 보드 기본 설정
        board = new int[n][n];
        k = sc.nextInt();

        // 사과 위치 설정
        for (int i = 0; i < k; i++) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            board[x][y] = 1; // 사과 위치
        }

        l = sc.nextInt();
        for (int i = 0; i < l; i++) {
            int x = sc.nextInt();
            char c = sc.next().charAt(0);
            directions.put(x, c);
        }

        snake.add(new int[] {0, 0}); // 초기 뱀 위치

        System.out.println(simulate());
    }

    static int simulate() {
        int time = 0;

        while (true) {
            time++;

            // 뱀의 머리 위치
            int[] head = snake.get(0);
            int nx = head[0] + dx[direction];
            int ny = head[1] + dy[direction];

            // 게임 종료 조건: 벽에 부딪힘 또는 자기 몸에 부딪힘
            if (nx < 0 || ny < 0 || nx >= n || ny >= n || isBody(nx, ny)) {
                break;
            }

            // 새로운 머리 위치 추가
            snake.add(0, new int[] {nx, ny});

            // 사과가 없다면 꼬리 제거
            if (board[nx][ny] == 1) {
                board[nx][ny] = 0;
            } else {
                snake.remove(snake.size() - 1);
            }

            // 방향 변환
            if (directions.containsKey(time)) {
                char turn = directions.get(time);
                if (turn == 'L') {
                    direction = (direction + 3) % 4; // 왼쪽 회전
                } else {
                    direction = (direction + 1) % 4; // 오른쪽 회전
                }
            }
        }

        return time;
    }

    static boolean isBody(int x, int y) {
        for (int[] pos : snake) {
            if (pos[0] == x && pos[1] == y) {
                return true;
            }
        }
        return false;
    }
}
