package implementation3;

import java.util.*;
import java.io.*;

public class 오목 {
    static int[][] map = new int[19][19];
    static int[] dirRow = new int[]{-1, 0, 1, 1};
    static int[] dirCol = new int[]{1, 1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 19; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 0; j < 19; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (map[i][j] > 0) {
                    if (isFiveInARow(i, j)) {
                        System.out.println(map[i][j]);
                        System.out.println((i + 1) + " " + (j + 1));
                        return;
                    }
                }
            }
        }

        System.out.println(0);
    }

    static boolean isFiveInARow(int row, int col) {
        int color = map[row][col];
        for (int d = 0; d < 4; d++) {
            int count = 1;

            // 한 방향 체크
            int nr = row + dirRow[d];
            int nc = col + dirCol[d];
            while (validate(nr, nc) && map[nr][nc] == color) {
                count++;
                nr += dirRow[d];
                nc += dirCol[d];
            }

            // 반대 방향 체크
            nr = row - dirRow[d];
            nc = col - dirCol[d];
            while (validate(nr, nc) && map[nr][nc] == color) {
                count++;
                nr -= dirRow[d];
                nc -= dirCol[d];
            }

            if (count == 5) {
                // 육목 확인 (육목이면 false)
                int backRow = row - dirRow[d];
                int backCol = col - dirCol[d];
                int nextRow = row + 5 * dirRow[d];
                int nextCol = col + 5 * dirCol[d];
                if (validate(backRow, backCol) && map[backRow][backCol] == color) continue;
                if (validate(nextRow, nextCol) && map[nextRow][nextCol] == color) continue;
                return true;
            }
        }
        return false;
    }

    static boolean validate(int row, int col) {
        return row >= 0 && row < 19 && col >= 0 && col < 19;
    }
}
