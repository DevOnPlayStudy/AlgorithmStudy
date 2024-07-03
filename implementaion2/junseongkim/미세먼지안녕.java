package implementaion2.junseongkim;

import java.io.*;

public class 미세먼지안녕 {

    static int[][] map;
    static int R;
    static int C;
    static int[][] spreadMap;
    static int[][] moveTemp;

    static int[] dirRow = new int[]{-1, 0, 1, 0};
    static int[] dirColumn = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        R = Integer.parseInt(split[0]);
        C = Integer.parseInt(split[1]);
        int T = Integer.parseInt(split[2]);

        map = new int[R][C];
        spreadMap = new int[R][C];
        moveTemp = new int[R][C];

        for (int i = 0; i < R; i++) {
            String[] split1 = br.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(split1[j]);
            }
        }

        // T초가 지날때
        for (int i = 0; i < T; i++) {
            System.out.println("=== map ===");
            debug(map);
            spread();
            System.out.println();

            System.out.println("=== spread ===");
            debug(map);

            System.out.println();

            System.out.println("===move===");
            dustMove();
            debug(map);
        }

        // 총 미세먼지량 구하기
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != -1) {
                    sum += map[i][j];
                }
            }
        }
        System.out.println("sum = " + sum);

    }

    static void dustMove() {
        moveTemp = map.clone();
        for (int i = (R / 2) - 3; i >= 0; i--) {
            moveTemp[i + 1][0] = map[i][0];
        }

        // 상부 행 왼쪽으로 한칸씩 보내기
        for (int i = 1; i < C; i++) {
            moveTemp[0][i - 1] = map[0][i];
        }

        // 오른쪽 위로 한칸씩 보내기
        for (int i = 1; i < R / 2; i++) {
            moveTemp[i - 1][C - 1] = map[i][C - 1];
        }

        // 밑에쪽 한칸식 오른쪽을 보내기
        for (int i = C - 2; i >= 0; i--) {
            moveTemp[(R / 2) - 1][i + 1] = map[(R / 2) - 1][i];
            if (moveTemp[(R / 2) - 1][i+1] == -1) {
                moveTemp[(R / 2) - 1][i+1] = 0;
            }
        }

        // 왼쪽 위로 이동
        for (int i = (R / 2) + 2; i < R; i++) {
            moveTemp[i - 1][0] = map[i][0];
        }

        // 아래쪽 이동
        for (int i = 1; i < C; i++) {
            moveTemp[R - 1][i - 1] = map[R - 1][i];
        }

        // 오른쪽 아래로 한칸씩 이동
        for (int i = R - 2; i >= R / 2; i--) {
            moveTemp[i + 1][C - 1] = map[i][C - 1];
        }

        // 위쪽 오른쪽으로 한칸씩
        for (int i = C - 2; i >= 0; i--) {
            moveTemp[R / 2][i + 1] = map[R / 2][i];
            if (moveTemp[R / 2][i+1] == -1) {
                moveTemp[R / 2][i+1] = 0;
            }
        }

        map = moveTemp.clone();

    }


    static void spread() {
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
    }
}
