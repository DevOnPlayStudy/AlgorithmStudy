package implementation3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 톱니바퀴 {
    static int[][] gears = new int[4][8];
    static int[] dir;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            String[] split = br.readLine().split("");
            gears[i] = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
        }

        int K = Integer.parseInt(br.readLine());


        for (int i = 0; i < K; i++) {
            String[] split = br.readLine().split(" ");
            int targetNum = Integer.parseInt(split[0]) - 1;
            int nowDir = Integer.parseInt(split[1]);
            dir = new int[4];

            dir[targetNum] = nowDir; // dir[0] = -1

            checkConnection(targetNum);
            turn();

        }

        // 점수 카운트
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            if (gears[i][0] == 1) {
                cnt += (int) Math.pow(2, i);
            }
        }
        System.out.println(cnt);
    }

    static void checkConnection(int targetNum) {
        // 타겟 기어 기준 왼쪽 연결 검사
        for (int i = targetNum - 1; i >= 0; i--) { // target = 0
            if (gears[i][2] != gears[i + 1][6]) {
                dir[i] = -dir[i + 1];
            }else{
                break; // 연결되어있지 않으면 for문 종료
            }
        }
        // 타겟 기어 기준 오른쪽 연결 검사
        for (int i = targetNum + 1; i < 4; i++) { // target = 1
            if (gears[i][6] != gears[i - 1][2]) { // gears[1][6] != gear[0][2] , gears[2][6] != gears[1][2]
                dir[i] = -dir[i - 1]; // dir[1] = -dir[0] = 1;
            }else{
                break;
            }
        }
    }

    static void turn() {
        for (int i = 0; i < 4; i++) {
            int[] temp = new int[8];
            if (dir[i] == 1) {
                temp[0] = gears[i][7];
                for (int j = 0; j < 7; j++) {
                    temp[j + 1] = gears[i][j];
                }
                gears[i] = temp.clone();
            }
            if(dir[i] == -1){
                temp[7] = gears[i][0];
                for (int j = 7; j > 0; j--) {
                    temp[j - 1] = gears[i][j];
                }
                gears[i] = temp.clone();
            }
        }
    }
}
