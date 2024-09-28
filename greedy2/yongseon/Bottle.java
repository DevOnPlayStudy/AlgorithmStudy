package greedy2.yongseon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Bottle {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] nk = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nk[0];
        int k = nk[1];

        int answer = 0;

        while (Integer.bitCount(n) > k) {
            n++;
            answer++;
        }

        System.out.println(answer);
    }
}
