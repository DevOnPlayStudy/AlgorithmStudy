package bfs2;

import java.io.*;
import java.util.*;

/**
 * 1. 입력
 *  1-1. N : 격자크기 = 2^N * 2^N
 *  1-2. Q : 시전 단계(L) 개수 1 , 2, 3, ... , Q
 *  1-3. 격자 입력 : int[][] map
 *
 * 2. 로직
 *  2-1. 시전단계 L 을 이용해 2^L * 2^L의 부분 격자 생성
 *  2-2. 시계방향으로 90도 회전
 *  2-3. 전체 map을 순환하며 3방향 이상에 어름이 없는 경우 해당 칸은 1 줄어듬 (4방향 검토는 상, 하, 좌, 우)
 *  2-4. 남은 L 만큼 2-1 ~ 2-3 반복
 *  2-5. 가장 큰 덩어리가 차지하는 칸 개수 구하는 것 => BFS
 *
 * 3. 출력
 *  3-1. 격자에 남아있는 얾음의 합을 출력
 *  3-2. 가장 큰 덩어리가 차지하는 칸의 개수를 출력
 */
public class 마법사상어와파이어스톰 {
    private static final String SEPARATOR = " ";
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    private static int tn;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz = new StringTokenizer(br.readLine(), SEPARATOR);

        int n = Integer.parseInt(stz.nextToken());
        int q = Integer.parseInt(stz.nextToken());
        tn = pow(n);

        int[][] map = new int[tn][tn];

        for (int i = 0; i < tn; i++) {
            stz = new StringTokenizer(br.readLine(), SEPARATOR);
            for (int j = 0; j < tn; j++) {
                map[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        // 1. q만큼 반복한다.
        stz = new StringTokenizer(br.readLine(), SEPARATOR);
        while (q-- > 0) {
            int l = Integer.parseInt(stz.nextToken());
            int size = pow(l);

            // 2. 범위 회전을 한다.
            int[][] rotated = new int[tn][tn];
            for (int i = 0; i < tn; i += size) {
                for (int j = 0; j < tn; j += size) {
                    rotateIce(map, rotated, i, j, size);
                }
            }

            map = rotated;

            // 3. 인접한 곳을 확인하여 얼음을 녹인다.
            melt(map);
        }

        boolean[][] visited = new boolean[tn][tn];
        int sum = 0;
        int size = 0;

        // 4. 남아있는 얼음 정보를 구한다.
        for(int i=0; i<tn; i++){
            for (int j = 0; j < tn; j++) {
                if(map[i][j] > 0 && !visited[i][j]){
                    int[] search = bfs(map, visited, i, j);

                    sum += search[0];
                    size = Math.max(size, search[1]);
                }
            }
        }

        bw.write(sum + System.lineSeparator() + size);

        bw.flush();
        bw.close();
        br.close();
    }

    private static int[] bfs(int[][] map, boolean[][] visited, int i, int j) {
        Queue<int[]> qu = new LinkedList<>();
        qu.add(new int[]{i ,j});
        visited[i][j] = true;

        int sum = 0;
        int size = 0;

        while (!qu.isEmpty()){
            int[] cn = qu.poll();

            sum += map[cn[0]][cn[1]];
            size++;

            for(int d=0; d<4; d++){
                int nx = cn[0] + dx[d];
                int ny = cn[1] + dy[d];

                if(!inRange(nx, ny) || map[nx][ny] < 1 || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                qu.add(new int[]{nx, ny});
            }
        }

        return new int[]{sum, size};
    }

    private static void melt(int[][] map) {
        List<int[]> meltIce = new ArrayList<>();

        for(int i=0; i<tn; i++){
            for (int j = 0; j < tn; j++) {
                int adjustIceCount = 0;

                if(map[i][j] < 1) continue;

                for(int d = 0; d<4; d++){
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if(!inRange(nx, ny)) continue;

                    if(map[nx][ny] > 0) adjustIceCount++;
                }

                if(adjustIceCount < 3){
                    meltIce.add(new int[]{i, j});
                }
            }
        }

        for (int[] ice : meltIce) {
            map[ice[0]][ice[1]] -= 1;
        }
    }

    private static boolean inRange(int x, int y){
        return (x>=0 && y>=0 && x<tn && y<tn);
    }

    private static void rotateIce(int[][] map, int[][] rotated, int x, int y, int size) {
        int ex = x + size;
        int ey = y + size;

        for(int i=x; i<ex; i++){
            int cnt = 0;
            for (int j = y; j < ey; j++) {
                rotated[i][j] = map[ex-1-cnt][size-(ex-i) + y];
                cnt++;
            }
        }
    }

    private static int pow(int num) {
        return (int) Math.pow(2, num);
    }
}
