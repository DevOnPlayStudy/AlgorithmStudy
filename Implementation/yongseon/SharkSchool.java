package Implementation.yongseon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SharkSchool {
    public static int n;
    public static int[][] classRoom;
    private static int[] colArr = new int[]{0, -1, 0, 1};
    private static int[] rowArr = new int[]{-1, 0, 1, 0};
    private static Map<Integer, int[]> favoriteNumberMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        classRoom = new int[n][n];

        for (int i=1; i<=n*n; i++) {
            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            if (i==1) {
                int myNum = nums[0];
                int[] favoriteNumbers = Arrays.copyOfRange(nums, 1 ,nums.length);
                favoriteNumberMap.put(myNum, favoriteNumbers);

                classRoom[1][1] = nums[0];
                continue;
            }

            int myNum = nums[0];
            int[] favoriteNumbers = Arrays.copyOfRange(nums, 1 ,nums.length);

            favoriteNumberMap.put(myNum, favoriteNumbers);

            findFavoriteStudentSpot(myNum, favoriteNumbers);
        }

        System.out.println(calculateSatisfaction());
    }

    // 그 학생과 인접한 칸에 앉은 좋아하는 학생의 수를 구해야 한다. 그 값이 0이면 학생의 만족도는 0, 1이면 1, 2이면 10, 3이면 100, 4이면 1000이다.
    public static int calculateSatisfaction() {
        int totalSatisfaction = 0;

        for (int row = 0; row < classRoom.length; row++) {
            for (int col = 0; col < classRoom[row].length; col++) {
                int myNum = classRoom[row][col];
                int favoriteCnt = 0;

                for(int rowIdx=0; rowIdx<rowArr.length; rowIdx++) {
                    int nextRow = row+rowArr[rowIdx];
                    int nextCol = col+colArr[rowIdx];

                    if (validation(nextRow, nextCol)) {
                        int targetNumber = classRoom[nextRow][nextCol];

                        if(isFavorite(targetNumber, favoriteNumberMap.get(myNum))) {
                            favoriteCnt++;
                        }
                    }
                }

                totalSatisfaction += (int) Math.pow(10, favoriteCnt - 1);
            }
        }

        return totalSatisfaction;
    }

    public static class Spot implements Comparable<Spot>  {
        private int col;

        private int row;

        private int emptyCount;

        private int favoriteCount;

        @Override
        public int compareTo(Spot o) {
            if(this.favoriteCount == o.favoriteCount) {
                if(this.emptyCount == o.emptyCount) {
                    if (this.row == o.row) {
                        return this.col - o.col;
                    } else {
                        return this.row - o.row;
                    }
                } else {
                    return o.emptyCount - this.emptyCount;
                }
            } else {
                return o.favoriteCount - this.favoriteCount;
            }
        }

        public Spot(int row, int col, int favoriteCount, int emptyCount) {
            this.col = col;
            this.row = row;
            this.favoriteCount = favoriteCount;
            this.emptyCount = emptyCount;
        }
    }

    public static void findFavoriteStudentSpot(int myNumber, int[] favoriteNumbers) {
        PriorityQueue<Spot> favoriteQueue = new PriorityQueue<>();

        for(int row=0; row<classRoom.length; row++) {
            for(int col=0; col<classRoom[row].length; col++) {
                int spot = classRoom[row][col];

                if (spot != 0) {
                    continue;
                }

                int favoriteCnt = 0;
                int emptyCnt = 0;

                for(int spotIdx=0; spotIdx < colArr.length; spotIdx++) {
                    int nextCol = col+colArr[spotIdx];
                    int nextRow = row+rowArr[spotIdx];

                    if(validation(nextCol, nextRow)) {
                        int targetNum = classRoom[nextRow][nextCol];

                        if(isFavorite(targetNum, favoriteNumbers)) {
                            favoriteCnt ++;
                        } else if(targetNum == 0) {
                            emptyCnt ++;
                        }

                        favoriteQueue.add(new Spot(row, col, favoriteCnt, emptyCnt));
                    }
                }
            }
        }

        Spot mySpot = favoriteQueue.poll();

        if (mySpot == null) {
            System.out.println(Arrays.toString(classRoom));
        }

        classRoom[mySpot.row][mySpot.col] = myNumber;
    }

    public static boolean isFavorite(int targetNum, int[] favoriteNumbers) {
        for (int favoriteNumber : favoriteNumbers) {
            if (favoriteNumber == targetNum) {
                return true;
            }
        }

        return false;
    }

    public static boolean validation(int x, int y) {
        return x>=0 && x<classRoom.length && y>=0 && y<classRoom.length;
    }
}
