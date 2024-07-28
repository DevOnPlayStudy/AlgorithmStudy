package bfs2.yongseon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FireStorm {
    private static int[][] map;
    private static Queue<Location> locations = new LinkedList<>();
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    private static boolean[][] visited;

    public static class Location {
        int x;
        int y;
        int value;

        public Location(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] nq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nq[0];

        int mapLength = (int) Math.pow(2, n);

        // 맵 크기 설정
        map = new int[mapLength][mapLength];
        visited = new boolean[mapLength][mapLength];

        // 맵에 존재하는 얼음 요소 넣기
        for (int i = 0; i < mapLength; i++) {
            int[] row = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < row.length; j++) {
                map[i][j] = row[j];
            }
        }

        // 격자 크기를 담은 배열
        int[] lArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int l: lArr) {
            int squareLength = (int) Math.pow(2, l);
            mapRotation(squareLength);
            minusIce();
        }

        int sum = 0;
        int size = 0;

        for(int i=0; i<map.length; i++){
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] > 0 && !visited[i][j]){
                    int[] search = bfs(i, j);

                    sum += search[0];
                    size = Math.max(size, search[1]);
                }
            }
        }

        System.out.println(sum + "\n" + size);
    }

    public static int[] bfs(int x, int y) {
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(x, y, map[x][y]));
        visited[x][y] = true;

        int sum = 0;
        int maxSize = 0;

        while (!queue.isEmpty()) {
            Location location = queue.poll();

            sum += location.value;
            maxSize++;

            for (int i = 0; i < dx.length; i++) {
                int nextX = location.x + dx[i];
                int nextY = location.y + dy[i];

                if (!validate(nextX, nextY) || map[nextX][nextY] < 1 || visited[nextX][nextY]) continue;

                visited[nextX][nextY] = true;
                queue.add(new Location(nextX, nextY, map[nextX][nextY]));
            }
        }

        return new int[]{sum, maxSize};
    }

    public static void minusIce() {
        List<int[]> meltIceList = new ArrayList<>();

        for (int y = 0;  y< map.length ; y++) {
            for (int x = 0; x < map[y].length; x++) {
                int totalCnt = 0;

                for (int i = 0; i < 4; i++) {
                    int nextX = x + dx[i];
                    int nextY = y + dy[i];

                    if (validate(nextX, nextY) && map[nextY][nextX] > 0) {
                        totalCnt++;
                    }
                }

                if (totalCnt < 3) {
                    meltIceList.add(new int[]{x, y});
                }
            }
        }

        for (int[] meltIce : meltIceList) {
            map[meltIce[1]][meltIce[0]] -= 1;
        }
    }

    public static void mapRotation(int squareLength) {
        for (int i = 0; i < map.length; i += squareLength) {
            for (int j = 0; j < map[0].length; j += squareLength) {
                rotate90Clockwise(i, j, squareLength);
            }
        }
    }

    public static void rotate90Clockwise(int x, int y, int length) {
        int[][] temp = new int[length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                temp[j][length - 1 - i] = map[x + i][y + j];
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                map[x + i][y + j] = temp[i][j];
            }
        }
    }

    private static boolean validate(int x, int y) {
        return x >= 0 && x < map[0].length && y >= 0 && y < map.length;
    }
}
