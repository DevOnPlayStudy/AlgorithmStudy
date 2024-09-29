package w13.yongseon;

import java.util.*;


public class 디펜스게임 {

    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < enemy.length; i++) {
            n -= enemy[i];
            maxHeap.add(enemy[i]);

            if (n < 0) {
                if (k > 0) {
                    int maxEnemy = maxHeap.poll();
                    n += maxEnemy;
                    k--;
                } else {
                    break;
                }
            }
            answer++;
        }
        return answer;
    }
}
