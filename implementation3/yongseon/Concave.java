package implementation3.yongseon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Concave {
    private static int[][] board = new int[19][19];
    private static int[] dx = new int[]{0, 1, 1};
    private static int[] dy = new int[]{1, 1, 0};
    private static final int MAX_COUNT = 5;
    private static PriorityQueue<Location> locations = new PriorityQueue<>();
    private static Set<Location> locationSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 보드 세팅
        for (int row = 0; row < 19; row++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int col = 0; col < arr.length; col++) {
                board[row][col] = arr[col];
            }
        }

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 0) {
                    continue;
                }

                if(locationSet.contains(new Location(col, row))) {
                    continue;
                }

                if(isWin(row, col)) {
                    Location winnerLocation = locations.poll();

                    System.out.println(board[winnerLocation.y][winnerLocation.x]);
                    System.out.println(winnerLocation.x + " " + winnerLocation.y);
                    return;
                }
            }
        }

        System.out.println(0);
    }

    public static boolean isWin(int row, int col) {
        for (int i = 0; i < dx.length; i++) {
            int count = 0;
            int nextRow = row + dy[i];
            int nextCol = col + dx[i];

            while (true) {
                if (!validation(nextRow, nextCol)) {
                    break;
                }

                if(board[nextRow][nextCol] != board[row][col]) {
                    break;
                } else {
                    count++;

                    locations.add(new Location(nextCol, nextRow));
                    locationSet.add(new Location(nextCol, nextRow));

                    nextRow += dy[i];
                    nextCol += dx[i];
                }
            }

            if (count == MAX_COUNT) {
                return true;
            } else {
                locations.clear();
            }
        }

        return false;
    }

    private static boolean validation(int row, int col) {
        return row < 19 && col < 19;
    }


    public static class Location implements Comparator<Location> {
        private int x;
        private int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compare(Location o1, Location o2) {
            if (o1.x == o2.x) {
                return o1.y - o2.y;
            }

            return o1.x - o2.x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Location location)) return false;
            return x == location.x && y == location.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
