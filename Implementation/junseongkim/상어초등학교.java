package Implementation.junseongkim;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 상어초등학교 {
    static class dimension implements Comparable<dimension> {
        private int nowIndex;
        private int row;
        private int column;
        private int count;
        private int emptyCnt;

        public dimension(int nowIndex, int row, int column, int count, int emptyCnt) {
            this.nowIndex = nowIndex;
            this.row = row;
            this.column = column;
            this.count = count;
            this.emptyCnt = emptyCnt;
        }

        @Override
        public int compareTo(dimension o) {
            if (this.count == o.count) {
                if (this.emptyCnt == o.emptyCnt) {
                    if (this.row == o.row) {
                        return this.column - o.column;
                    } else {
                        return this.row - o.row;
                    }
                } else {
                    return o.emptyCnt - this.emptyCnt;
                }
            } else {
                return o.count - this.count;
            }
        }
    }
    static int[][] map;
    static int[][] survey;
    static int N;
    static int[] dirRow = {-1, 0, 1, 0};
    static int[] dirColumn = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        survey = new int[N * N][5];
        map = new int[N][N];

        // 입력 받기 완료
        for (int i = 0; i < N * N; i++) {
            survey[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 첫번째는 1,1로 고정
        map[1][1] = survey[0][0];

        // 규칙 start
        for (int i = 1; i < N * N; i++) {
            condition1(i);
        }

        int sum = 0;
        for (int i = 0; i < survey.length; i++) {
            sum += condition2(i);
        }
        System.out.println(sum);

    }

    static void condition1(int nowIndex) {
        PriorityQueue<dimension> pq = new PriorityQueue<>();
        // 전체 map을 돈다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int count = 0;
                int emptyCount = 0;

                // 4방면 조회
                for (int k = 0; k < dirRow.length; k++) {
                    int nextRow = i + dirRow[k];
                    int nextColumn = j + dirColumn[k];

                    // map 유효성 검증 안에서만 수행
                    if (validate(nextRow, nextColumn)) {
                        int num = map[nextRow][nextColumn];

                        // 좋아하는 학생 번호가 map에 있는지
                        for (int l = 1; l < 5; l++) {
                            if (survey[nowIndex][l] == num) {
                                count++;
                            }
                        }
                        if (num == 0) {
                            emptyCount++;
                        }

                    }
                }
                pq.offer(new dimension(nowIndex, i, j, count, emptyCount));
            }
        }

        while (!pq.isEmpty()) {
            final dimension poll = pq.poll();

            if (map[poll.row][poll.column] == 0) {
                map[poll.row][poll.column] = survey[nowIndex][0];
                break;
            }

        }

    }

    static int condition2(int nowIndex) {
        int preferScore = 0;
        int sameCnt = 0;

        int studentNum = survey[nowIndex][0];

        // map 전체 돌기
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {

                // 타겟 학생번호를 찾았을 경우
                if (map[j][k] == studentNum) {
                    // 4방면을 뒤진다
                    for (int l = 0; l < 4; l++) {

                        int nextRow = j + dirRow[l];
                        int nextColumn = k + dirColumn[l];

                        // 검증
                        if (validate(nextRow, nextColumn)) {

                            // 4방면의 학생 번호 추출
                            int num = map[nextRow][nextColumn];

                            // 좋아하는 학생 번호가 map에 있는지
                            for (int m = 1; m < 5; m++) {
                                // 2 5 4 7 == ?
                                if (survey[nowIndex][m] == num) {
                                    sameCnt++;
                                }
                            }
                        }
                    }
                }

            }
        }
        if (sameCnt == 1) {
            preferScore += 1;
        } else if (sameCnt == 2) {
            preferScore += 10;
        } else if (sameCnt == 3) {
            preferScore += 100;
        } else if (sameCnt == 4) {
            preferScore += 1000;
        } else {
            preferScore += 0;
        }
        return preferScore;
    }

    static boolean validate(int nextRow, int nextColumn) {
        return nextRow >= 0 && nextColumn >= 0 && nextRow < N && nextColumn < N;
    }

}

