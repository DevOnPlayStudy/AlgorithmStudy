package implementaion2.junseongkim;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class 미세먼지안녕 {

    static int[][] map;
    static int R, C;
    static int[][] moveTemp;
    static List<Integer> purifier = new ArrayList<>();
    static int[] dirRow = new int[]{-1, 0, 1, 0};
    static int[] dirColumn = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        R = Integer.parseInt(split[0]);
        C = Integer.parseInt(split[1]);
        int T = Integer.parseInt(split[2]);

        map = new int[R][C];
        moveTemp = new int[R][C];

        for (int i = 0; i < R; i++) {
            String[] split1 = br.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(split1[j]);
                if (map[i][j] == -1) {
                    purifier.add(i);
                }
            }
        }

        // T초가 지날때
        for (int i = 0; i < T; i++) {
            spread();
            dustMove();
        }

        // 총 미세먼지량 구하기
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > -1) {
                    sum += map[i][j];
                }
            }
        }
        System.out.println(sum);
    }

    static void dustMove() {
        moveTemp = map.clone();

        int firstRow = purifier.get(0); // 상부 공기청정기 row 위치
        int lastRow = purifier.get(1); // 하부 공기청정기 row 위치

        // 상부 공기청정기
        // 왼쪽
        for (int i = firstRow - 1; i >= 0; i--) {
            moveTemp[i + 1][0] = map[i][0];
            moveTemp[firstRow][0] = -1;
        }
        // 위쪽
        for (int i = 1; i < C; i++) {
            moveTemp[0][i - 1] = map[0][i];
        }

        // 오른쪽
        for (int i = 1; i <= firstRow; i++) {
            moveTemp[i - 1][C - 1] = map[i][C - 1];
        }
        // 아래쪽
        for (int i = C - 2; i >= 0; i--) {
            moveTemp[firstRow][i + 1] = map[firstRow][i];
            if (moveTemp[firstRow][i + 1] == -1) {
                moveTemp[firstRow][i + 1] = 0;
            }
        }


        // 하부 공기청정기
        // 왼쪽
        for (int i = lastRow + 1; i < R; i++) { // lastRow : 4, 5, 6
            moveTemp[i - 1][0] = map[i][0];
            moveTemp[lastRow][0] = -1;
        }
        // 아래쪽
        for (int i = 1; i < C; i++) {
            moveTemp[R - 1][i - 1] = map[R - 1][i];
        }
        // 오른쪽
        for (int i = R - 2; i >= lastRow; i--) {
            moveTemp[i + 1][C - 1] = map[i][C - 1];
        }
        // 위쪽
        for (int i = C - 2; i >= 0; i--) {
            moveTemp[lastRow][i + 1] = map[lastRow][i];
            if (moveTemp[lastRow][i + 1] == -1) {
                moveTemp[lastRow][i + 1] = 0;
            }
        }
        map = moveTemp.clone();
    }

    static void spread() {
        int[][] spreadMap = new int[R][C];

        // 2 중 포문으로 map을 확인
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {

                if (map[i][j] > 0) {
                    int spreadCnt = 0;

                    for (int k = 0; k < 4; k++) {
                        int nextRow = i + dirRow[k];
                        int nextColumn = j + dirColumn[k];

                        if (validate(nextRow, nextColumn) && map[nextRow][nextColumn] != -1) {
                            spreadMap[nextRow][nextColumn] = spreadMap[nextRow][nextColumn] + map[i][j] / 5;
                            spreadCnt++;
                        }
                    }
                    map[i][j] = map[i][j] - (map[i][j] / 5) * spreadCnt;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] += spreadMap[i][j];
            }
        }
    }

    static boolean validate(int nextRow, int nextColumn) {
        return nextRow >= 0 && nextColumn >= 0 && nextRow < R && nextColumn < C;
    }

    static void debug(int[][] arr) {
        for (int[] a : arr) {
            for (int b : a) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

