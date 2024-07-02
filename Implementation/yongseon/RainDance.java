package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RainDance {

    private static int[][] space;
    private static int N;
    private static int M;
    private static int turn = 0;
    private static Cloud cloud;
    private static int[][] firstLocations = new int[][] {{1, 0}, {1, 1}, {2, 0}, {2, 1}};
    private static int[][] diagonal = new int[][] {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
    private static Queue<int[]> copiedWaters = new LinkedList<>();

    /**
     * 모든 구름이 di 방향으로 si칸 이동한다.
     * 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
     * 구름이 모두 사라진다.
     * 2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다. 물복사버그 마법을 사용하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
     * 이때는 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
     * 예를 들어, (N, 2)에서 인접한 대각선 칸은 (N-1, 1), (N-1, 3)이고, (N, N)에서 인접한 대각선 칸은 (N-1, N-1)뿐이다.
     * 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
     */
    public static void main(String ars[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = nm[0];
        M = nm[1];
        space = new int[N][N];
        setSpace(br);
        cloud = new Cloud(new ArrayList<>(), N);

        createCloud();

        for (int i = 0; i < M; i++) {
            int[] command = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int direction = command[0];
            int move = command[1];

            cloud.move(direction, move);
            rainDance();
            copyWater();
            createCloud();
        }

        System.out.println(calculateTotalWater());
    }

    public static class Cloud {
        private List<int[]> locations;

        private int maxSize;

        public void resetLocations(List<int[]> locations) {
            this.locations = locations;
        }

        public Cloud(List<int[]> locations, int maxSize) {
            this.locations = locations;
            this.maxSize = maxSize;
        }

        public boolean isContain(int[] targetLocation) {
            for (int[] location : locations) {
                if (Arrays.equals(location, targetLocation)) {
                    return true;
                }
            }

            return false;
        }

        public void move(int direction, int move) {
            switch (direction) {
                case 1:
                    changeLocations(0, -move);
                    break;
                case 2:
                    changeLocations(-move, -move);
                    break;
                case 3:
                    changeLocations(-move, 0);
                    break;
                case 4:
                    changeLocations(-move, move);
                    break;
                case 5:
                    changeLocations(0, move);
                    break;
                case 6:
                    changeLocations(move, move);
                    break;
                case 7:
                    changeLocations(move, 0);
                    break;
                case 8:
                    changeLocations(move,-move);
                    break;
            }
        }

        private void changeLocations(int moveRow, int moveCol) {
            for (int i = 0; i < locations.size(); i++) {
                int[] location = locations.get(i);
                int row = location[0];
                int col = location[1];

                // -4%4
                int newRow = (row + moveRow) % maxSize;

                if (newRow < 0) {
                    newRow += maxSize;
                }

                int newCol = (col + moveCol) % maxSize;

                if (newCol < 0) {
                    newCol += maxSize;
                }

                locations.set(i, new int[]{newRow, newCol});
            }
        }
    }

    private static void setSpace(BufferedReader br) throws IOException{
        for (int i = 0; i < N; i++) {
            int [] row = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < row.length; j++) {
                space[i][j] = row[j];
            }
        }
    }

    private static void createCloud() {
        if(turn == 0) {
            List<int[]> locations = new ArrayList<>();

            for (int i = 0; i < firstLocations.length; i++) {
                locations.add(new int[]{N-firstLocations[i][0], firstLocations[i][1]});
            }

            cloud.resetLocations(locations);
            turn++;
        } else {
            List<int[]> nextCloudLocations = new ArrayList<>();

            for (int row = 0; row < space.length; row++) {
                for (int col = 0; col < space[row].length; col++) {
                    if (!cloud.isContain(new int[]{row, col}) && space[row][col] >= 2) {
                        nextCloudLocations.add(new int[] {row, col});
                        space[row][col] -= 2;
                    }
                }
            }

            cloud.resetLocations(nextCloudLocations);
            turn++;
        }
    }

    private static void rainDance() {
        for (int i = 0; i < cloud.locations.size(); i++) {
            int[] cloudLocation = cloud.locations.get(i);

            space[cloudLocation[0]][cloudLocation[1]] += 1;
        }
    }

    private static void copyWater() {
        for (int i = 0; i < cloud.locations.size(); i++) {
            int totalIncrement = 0;
            int[] cloudLocation = cloud.locations.get(i);

            for (int j = 0; j < diagonal.length; j++) {
                int tempRow = cloudLocation[0] + diagonal[j][0];
                int tempCol = cloudLocation[1] + diagonal[j][1];

                if (exists(tempRow, tempCol)) {
                    if (space[tempRow][tempCol] > 0) {
                        totalIncrement += 1;
                    }
                }
            }

            copiedWaters.add(new int[]{cloudLocation[0], cloudLocation[1], totalIncrement});
        }

        while (!copiedWaters.isEmpty()) {
            int[] location = copiedWaters.poll();

            space[location[0]][location[1]] += location[2];
        }
    }

    private static boolean exists(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }

    private static int calculateTotalWater() {
        int totalWater = 0;

        for (int row = 0; row < space.length; row++) {
            for (int col = 0; col < space[row].length; col++) {
                totalWater += space[row][col];
            }
        }

        return totalWater;
    }
}
