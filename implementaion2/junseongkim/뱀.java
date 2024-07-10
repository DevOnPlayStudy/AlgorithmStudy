package implementaion2.junseongkim;

import java.util.*;
import java.io.*;

public class 뱀 {

    static int N, K, L; // 맵 크기, 사과 갯수, 방향 전환 횟수
    static int[][] map;

    static HashMap<Integer, String> directions = new HashMap<>();
    static List<int[]> snake = new ArrayList<>();

    static int[] dirRow = new int[]{0, 1, 0, -1}; // 0 ~ 3 시계방향
    static int[] dirCol = new int[]{1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); //정사각형 크기
        map = new int[N][N];

        K = Integer.parseInt(br.readLine()); //사과 갯수, 좌표

        for (int i = 0; i < K; i++) {
            String[] split = br.readLine().split(" ");
            int appleRow = Integer.parseInt(split[0]) - 1;
            int appleCol = Integer.parseInt(split[1]) - 1;
            map[appleRow][appleCol] = 1;
        }

        L = Integer.parseInt(br.readLine());// 방향전환 갯수, 좌표
        for (int i = 0; i < L; i++) {
            String[] str2 = br.readLine().split(" ");
            int checkTime = Integer.parseInt(str2[0]);
            String str = str2[1];
            directions.put(checkTime, str);
        }

        move();
    }

    static void move() {
        int currentRow = 0, currentCol = 0;
        int time = 0;
        int currentDir = 0;

        snake.add(new int[]{currentRow, currentCol});

        while (true) {
            // 1. 시간 체크
            time++;

            // 2 뱀 머리 이동
            int nextRow = currentRow + dirRow[currentDir];
            int nextCol = currentCol + dirCol[currentDir];

            // 3. 유효 체크 : 맵 바깥이거나, 뱀 몸통에 다을 경우
            if (validate(nextRow, nextCol)) {
                break;
            }

            // 4. 사과가 있을 경우
            if (map[nextRow][nextCol] == 1) {
                map[nextRow][nextCol] = 0; // 사과를 먹음
                snake.add(new int[]{nextRow, nextCol}); // 늘어남
            } else {
                // 5. 사과가 없을 경우
                snake.add(new int[]{nextRow, nextCol}); // 머리 이동
                snake.remove(0); // 꼬리가 줄어든다
            }

            // 6. 방향 바꾸기 체크
            if (directions.containsKey(time)) {
                if (directions.get(time).equals("D")) { // 우회전
                    currentDir = (currentDir + 1) % 4;
                } else { // 좌회전
                    currentDir = (currentDir - 1 + 4) % 4;
                }
            }

            // 7. 위치 초기화
            currentRow = nextRow;
            currentCol = nextCol;
        }
        System.out.println(time);
    }

    static boolean validate(int nextRow, int nextCol) {
        if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
            return true;
        }

        for (int[] body : snake) { // snake 리스트에 있는 index 일 경우 true
            if (body[0] == nextRow && body[1] == nextCol) {
                return true;
            }
        }
        return false;
    }

}

