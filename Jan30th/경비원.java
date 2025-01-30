package Jan30th;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 경비원 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int width = Integer.parseInt(st.nextToken()); // 블록 가로
        int length = Integer.parseInt(st.nextToken()); // 블록 세로
        int shopQty = Integer.parseInt(br.readLine());
        int cnt = 0, company = 0;

        int[] map = new int[shopQty];

        for (int i = 0; i < shopQty + 1; i++) {
            st = new StringTokenizer(br.readLine());

            int dir = Integer.parseInt(st.nextToken());
            int loc = Integer.parseInt(st.nextToken());
            int tmp = 0;

            // 좌측 상단 0 점을 기준이로 길이를 계산
            switch (dir) {
                case 1:
                    tmp = loc;
                    break;
                case 2:
                    tmp = width + length + width - loc;
                    break;
                case 3:
                    tmp = width + length + width + length - loc;
                    break;
                case 4:
                    tmp = width + loc;
                    break;
            }

            if (i < shopQty) {
                map[i] = tmp;
            } else {
                company = tmp;
            }
        }

        for (int i = 0; i < shopQty; i++) {
            int path1 = Math.abs(company - map[i]);
            int path2 = (2 * width + 2 * length) - path1; // 블록 전체길이 - (상점과 회사 사이 거리)
            cnt += Math.min(path1, path2);
        }

        System.out.println(cnt);
    }
}
