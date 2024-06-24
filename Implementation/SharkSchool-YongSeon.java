package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SharkSchool {
    public static int n;
    public static int[][] classRoom;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        classRoom = new int[n][n];

        for (int i=1; i<=n*n; i++) {
            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            if (i==1) {
                classRoom[1][1] = nums[0];
                continue;
            }

            findFavoriteStudentSpot(nums[0], Arrays.copyOfRange(nums, 1 ,nums.length));
        }

        System.out.println(Arrays.toString(classRoom));
    }

    public static class Spot implements Comparable<Spot>  {
        private int col;

        private int row;

        @Override
        public int compareTo(Spot o) {

            if (this.col == o.col) {
                return o.row - this.row;
            }

            return col;
        }

        public Spot(int col, int row) {
            this.col = col;
            this.row = row;
        }
    }

    public static void findFavoriteStudentSpot(int myNumber, int[] favoriteNumbers) {
        int maximumFavoriteNumbers = 0;
        int maximumEmptySpotCnt = 0;
        PriorityQueue<Spot> favoriteQueue = new PriorityQueue<>();

        for(int i=0; i<classRoom.length; i++) {
            for(int j=0; j<classRoom[i].length; j++) {
                int favoriteNumCnt = 0;
                int emptySpots = 0;

                int spot = classRoom[i][j];

                if (spot != 0) {
                    continue;
                }

                if(j>0) {
                    int targetNum = classRoom[i][j-1];

                    if (isFavorite(targetNum, favoriteNumbers)) {
                        favoriteNumCnt += 1;
                    } else if (targetNum == 0) {
                        emptySpots += 1;
                    }
                }

                if(i>0) {
                    int targetNum = classRoom[i-1][j];

                    if (isFavorite(targetNum, favoriteNumbers)) {
                        favoriteNumCnt += 1;
                    } else if (targetNum == 0) {
                        emptySpots += 1;
                    }
                }

                if(j+1 < classRoom[i].length) {
                    int targetNum = classRoom[i][j+1];

                    if (isFavorite(targetNum, favoriteNumbers)) {
                        favoriteNumCnt += 1;
                    } else if (targetNum == 0) {
                        emptySpots +=1;
                    }
                }

                if(i+1 < classRoom.length) {
                    int targetNum = classRoom[i+1][j];

                    if (isFavorite(targetNum, favoriteNumbers)) {
                        favoriteNumCnt += 1;
                    } else if (targetNum == 0) {
                        emptySpots +=1;
                    }
                }

                if(maximumFavoriteNumbers < favoriteNumCnt) {
                    favoriteQueue.clear();

                    maximumFavoriteNumbers = favoriteNumCnt;
                    favoriteQueue.add(new Spot(i, j));
                } else if(maximumFavoriteNumbers == favoriteNumCnt && emptySpots > maximumEmptySpotCnt) {
                    favoriteQueue.clear();

                    maximumEmptySpotCnt = emptySpots;
                    favoriteQueue.add(new Spot(i, j));
                } else if (maximumFavoriteNumbers == favoriteNumCnt && emptySpots == maximumEmptySpotCnt) {
                    favoriteQueue.clear();

                    favoriteQueue.add(new Spot(i, j));
                }
            }
        }

        Spot mySpot = favoriteQueue.poll();

        if (mySpot == null) {
            System.out.println(Arrays.toString(classRoom));
        }

        classRoom[mySpot.col][mySpot.row] = myNumber;
    }

    public static boolean isFavorite(int targetNum, int[] favoriteNumbers) {
        for (int favoriteNumber : favoriteNumbers) {
            if (favoriteNumber == targetNum) {
                return true;
            }
        }

        return false;
    }
}
