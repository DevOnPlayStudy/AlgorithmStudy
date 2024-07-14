package implementaion2.yongseon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ByeDust {
    private static int[][] house;
    private static Queue<Dust> dustQueue = new LinkedList<>();
    private static List<Dust> dustTopList = new ArrayList();
    private static int[][] checkList = new int[][] {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    // 공기 청정기 위에 위치
    private static int top;
    // 공기 청정기 아래 위치
    private static int bottom;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] RCT = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        house = new int[RCT[0]][RCT[1]];
        setHouse(br);

        // 공기 청정기 위치 찾기
        for (int i = 0; i < house.length; i++) {
            if(house[i][0] == -1) {
                top = i;
                bottom = i+1;
                break;
            }
        }

        for (int i = 0; i < RCT[2]; i++) {
            spreadAll();
            moveTopWind();
            moveBottomWind();
        }

        int totalDust = 0;

        for (int row = 0; row < house.length; row++) {
            for (int col = 0; col < house[row].length; col++) {
                if(house[row][col] > 0) {
                    totalDust += house[row][col];
                }
            }
        }

        System.out.println(totalDust);
    }

    // 집 공간 설정하기
    private static void setHouse(BufferedReader br) throws IOException{
        for (int i = 0; i < house.length; i++) {
            int[] row = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int col = 0; col < row.length; col++) {
                house[i][col] = row[col];
            }
        }
    }

    private static void moveBottomWind() {
        // 가장 왼쪽에서 일어나는 공기 움직임
        for (int row = bottom+1; row < house.length-1; row++) {
            house[row][0] = house[row+1][0];
        }

        // 가장 아래쪽에서 일어나는 공기 움직임
        for (int col = 0; col < house[0].length-1; col++) {
            int row = house.length-1;

            house[row][col] = house[row][col+1];
        }

        // 가장 오른쪽에서 일어나는 공기 움직임
        for (int row = house.length-1; row > bottom; row--) {
            int col = house[row].length-1;

            house[row][col] = house[row-1][col];
        }

        // 가장 위쪽에서 일어나는 공기 움직임
        for (int col = house[bottom].length-1; col > 0; col--) {
            int row = bottom;

            if(col == 1) {
                house[row][col] = 0;
            } else {
                house[row][col] = house[row][col-1];
            }
        }
    }

    private static void moveTopWind() {
        // 가장 왼쪽에서 일어나는 공기 움직임
        for (int row = top-1; row > 0; row--) {
            house[row][0] = house[row-1][0];
        }

        // 가장 위쪽에서 일어나는 공기 움직임
        for (int col = 0; col < house[0].length-1; col++) {

            house[0][col] = house[0][col+1];
        }

        // 가장 오른쪽에서 일어나는 공기 움직임
        for (int row = 0; row < top; row++) {
            int col = house[row].length-1;
            house[row][col] = house[row+1][col];
        }

        // 가장 아래쪽에서 일어나는 공기 움직임
        for (int col = house[top].length-1; col > 0; col--) {
            if(col == 1) {
                house[top][col] = 0;
            } else {
                house[top][col] = house[top][col-1];
            }
        }
    }

    // 먼지 확산하는 메서드
    private static void spreadAll() {
        for (int row = 0; row < house.length; row++) {
            for (int col = 0; col < house[row].length; col++) {
                // 먼지값
                int dustValue = house[row][col];

                // 확산되는 먼지의 값
                int spreadDustValue =  (int) Math.floor(dustValue/5);

                // 먼지가 없거나 공기 청정기가 있으면 스킵
                if(dustValue == 0 || dustValue == -1) {
                    continue;
                }

                // 확산되는 먼지 수
                int spreadCount = appendDust(row, col, spreadDustValue);

                // 현재 먼지 수 - 확산되는 먼지 값*확산되는 먼지 수
                house[row][col] = dustValue - spreadDustValue*spreadCount;
            }
        }

        // 큐에 등록된 먼지들 확산 시키기
        while (!dustQueue.isEmpty()) {
            Dust dust = dustQueue.remove();

            house[dust.row][dust.col] += dust.value;
        }
    }

    // 확산되는 더스트를 큐에 미리 적재
    private static int appendDust(int row, int col, int dust) {
        int spreadCount = 0;

        for (int i = 0; i < checkList.length; i++) {
            int tempRow = row + checkList[i][0];
            int tempCol = col + checkList[i][1];

            if(validate(tempRow, tempCol, dust)) {
                dustQueue.add(new Dust(tempRow, tempCol, dust));
                spreadCount++;
            }
        }

        return spreadCount;
    }

    public static class Dust {
        private int row;
        private int col;
        private int value;
        private boolean isEmpty;

        public Dust(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

    private static boolean validate(int row, int col, int dust) {
        if(row < 0 || row >= house.length || col < 0 || col >= house[row].length) {
            return false;
        } else if (house[row][col] == -1) {
            return false;
        } else if (dust==0) {
            return false;
        }

        return true;
    }
}
