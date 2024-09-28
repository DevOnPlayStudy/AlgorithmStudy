package greedy2.yongseon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Tashu {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;

        int n = Integer.parseInt(br.readLine());

        int[] before = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] after = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < n; i++) {
            int beforeX = before[i];
            int afterX = after[i];

            if(beforeX < afterX) {
                int underBike = afterX - beforeX;

                for (int j = 0; j < n; j++) {
                    if(before[j] < after[j]) {
                        int overBike = after[j] - before[j];

                        int addBike = Math.min(underBike, overBike);

                        before[j] += addBike;
                        after[i] -= addBike;
                        answer += addBike;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
