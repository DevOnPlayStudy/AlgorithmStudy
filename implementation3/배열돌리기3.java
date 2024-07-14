package implementation3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 배열돌리기3 {
    static int[][] map;
    static int N, M;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        // 배열 크기
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        map = new int[N][M];

        // 돌리기 배열
        int R = Integer.parseInt(split[2]);

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        String[] turns = br.readLine().split(" ");
        for (int i = 0; i < R; i++) {
            if (turns[i].charAt(0) == '1') {
                turn1();
            }
            if (turns[i].charAt(0) == '2') {
                turn2();
            }
            if (turns[i].charAt(0) == '3') {
                turn3();
            }
            if (turns[i].charAt(0) == '4') {
                turn4();
            }
            if (turns[i].charAt(0) == '5') {
                turn5();
            }
            if (turns[i].charAt(0) == '6') {
                turn6();
            }
        }

        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    // 상하 반전
    static void turn1() {
        int[][] temp = new int[map.length][map[0].length];
        for (int i = 0; i < N; i++) {
            temp[i] = map[N - 1 - i].clone();
        }
        map = temp.clone();

    }

    // 좌우 반전
    static void turn2() {
        int[][] temp = new int[map.length][map[0].length];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = map[i][M - 1 - j];
            }
        }
        map = temp.clone();
    }

    // 오른쪽으로 90도 회전
    static void turn3() {
        int[][] temp = new int[map[0].length][map.length];
        N = temp.length;
        M = temp[0].length;
        for (int i = 0; i < temp[0].length; i++) {
            for (int j = 0; j < temp.length; j++) {
                temp[j][temp[0].length - 1 - i] = map[i][j];
            }
        }
        map = temp.clone();
    }

    // 왼쪽으로 90도 회전
    static void turn4() {
        int[][] temp = new int[map[0].length][map.length];
        N = temp.length;
        M = temp[0].length;
        for (int i = 0; i < temp[0].length; i++) {
            for (int j = 0; j < temp.length; j++) {
                temp[temp.length - 1 - j][i] = map[i][j];
            }
        }
        map = temp.clone();
    }

    // 4개의 부분 배열 시계방향으로 돌리기
    static void turn5() {
        int[][] temp = new int[map.length][map[0].length];


        // 1번 그룹 -> 2번 그룹
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < M / 2; j++) {
                temp[i][M / 2 + j] = map[i][j];
            }
        }

        // 2번 그룹 -> 3번 그룹
        for (int i = 0; i < M / 2; i++) {
            for (int j = 0; j < N / 2; j++) {
                temp[N / 2 + j][M / 2 + i] = map[j][M / 2 + i];
            }
        }

        // 3번 그룹 -> 4번 그룹
        for (int i = N / 2; i < N; i++) {
            for (int j = 0; j < M / 2; j++) {
                temp[i][j] = map[i][M / 2 + j];
            }
        }

        // 4번 그룹 -> 1번 그룹
        for (int i = 0; i < M / 2; i++) {
            for (int j = 0; j < N / 2; j++) {
                temp[j][i] = map[N / 2 + j][i];
            }
        }
        map = temp.clone();
    }

    static void turn6() {
        int[][] temp = new int[map.length][map[0].length];

        // 1번 그룹 -> 4번 그룹
        for (int i = 0; i < M / 2; i++) {
            for (int j = 0; j < N / 2; j++) {
                temp[N / 2 + j][i] = map[j][i];
            }
        }

        // 2번 그룹 -> 1번 그룹
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < M / 2; j++) {
                temp[i][j] = map[i][M / 2 + j];
            }
        }

        // 3번 그룹 -> 2번 그룹
        for (int i = 0; i < M / 2; i++) {
            for (int j = 0; j < N / 2; j++) {
                temp[j][M / 2 + i] = map[N / 2 + j][M / 2 + i];
            }
        }

        // 4번 그룹 -> 3번 그룹
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < M / 2; j++) {
                temp[N / 2 + i][M / 2 + j] = map[N / 2 + i][j];
            }
        }
        map = temp.clone();
    }
}
