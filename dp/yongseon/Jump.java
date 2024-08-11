package dp.yongseon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Jump {
    private static int[][] map;
    private static long[][] dpMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        dpMap = new long[N][N];

        for (int i = 0; i < N; i++) {
            int[] cols = Arrays.stream(br.readLine()
                            .split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int j = 0; j < cols.length; j++) {
                map[i][j] = cols[j];
            }
        }

        setDpMap(N);

        System.out.println(dpMap[N-1][N-1]);
    }

    public static void setDpMap(int N) {
        dpMap[0][0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 || dpMap[i][j] == 0) {
                    continue;
                }

                int jumpSize = map[i][j];

                if (i+jumpSize < map.length) {
                    dpMap[i+jumpSize][j] += dpMap[i][j];
                }

                if (j+jumpSize < map.length) {
                    dpMap[i][j+jumpSize] += dpMap[i][j];
                }
            }
        }
    }
}
