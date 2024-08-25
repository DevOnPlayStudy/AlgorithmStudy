package greedy2.yongseon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sensor {
    static List<Integer> distances = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int k = Integer.parseInt(br.readLine());

        int[] sensors = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        for (int i=0; i<n-1; i++) {
            distances.add(sensors[i+1] - sensors[i]);
        }

        Collections.sort(distances);

        int answer = 0;

        for (int j=0; j<n-k; j++) {
            answer += distances.get(j);
        }

        System.out.println(answer);
    }
}
