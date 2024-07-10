package implementaion2.junseongkim;

import java.util.*;
import java.io.*;

public class 경사로 {

    static int arr[][];
    static int L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        int origin[][] = new int[N][N];
        arr = new int[N * 2][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken());
                arr[i][j] = origin[i][j];
            }
        }

        int idx = N;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[idx][j] = origin[j][i];
            }
            idx++;
        }

        boolean slider[][];
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            slider = new boolean[arr[0].length][2];

            //경사로를 놓음.
            putSlider(slider, i);

            boolean check = true;

            for (int j = 1; j < arr[0].length; j++) {
                //이전 높이와 현재 높이가 같다면 continue
                //현재 높이가 이전높이보다 1 낮고, 현재 높이에 내려가는 경사로가 있다면 continue
                //현재 높이가 이전높이보다 1 크고, 이전 높이에 올라가는 경사로가 있다면 continue
                if (arr[i][j] == arr[i][j - 1]) continue;
                if (arr[i][j] - arr[i][j - 1] == -1 && slider[j][0]) continue;
                if (arr[i][j] - arr[i][j - 1] == 1 && slider[j - 1][1]) continue;

                //이외의 경우에는 이전 좌표에서 현재 좌표로 이동할 수 없음.
                check = false;
                break;
            }

            //시작 위치에서 끝 위치까지 도달 가능. count 증가
            if (check) count++;

        }

        System.out.println(count);


    }

    //경사로를 놓는 함수.
    public static void putSlider(boolean check[][], int idx) {

        //현재 위치에서 경사로를 놓을 수 있는지 check.
        //내려가는 경사로를 우선 설치
        for (int i = 1; i < arr[0].length - L + 1; i++) {

            //현재 위치가 이전 위치보다 낮다면
            if (arr[idx][i - 1] - arr[idx][i] == 1) {
                boolean put = true;
                int stan = arr[idx][i];

                //L개 만큼 높이가 같나 체크.
                for (int j = 0; j < L; j++)
                    if (arr[idx][i + j] != stan) put = false;

                //설치 가능하면 설치
                if (put) {
                    for (int j = 0; j < L; j++)
                        check[i + j][0] = true;
                }
            }
        }

        //올라가는 경사로를 설치
        for (int i = arr[0].length - 1; i >= L; i--) {

            //현재 위치가 이전 위치보다 높다면
            if (arr[idx][i] - arr[idx][i - 1] == 1) {
                boolean put = true;
                int stan = arr[idx][i - 1];

                //L개만큼 높이가 같나 체크.
                //내려가는 경사로가 이미 설치되어 있나 체크.
                for (int j = 0; j < L; j++) {
                    if (arr[idx][i - j - 1] != stan) put = false;
                    if (check[i - j - 1][0]) put = false;
                }

                //설치 가능하면 설치
                if (put) {
                    for (int j = 0; j < L; j++)
                        check[i - j - 1][1] = true;
                }

            }
        }


    }


}
