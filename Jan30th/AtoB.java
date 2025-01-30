package Jan30th;

import java.util.*;

public class AtoB {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long startNum = sc.nextInt();
        long targetNum = sc.nextInt();

        Queue<Long> queue = new LinkedList<>();
        queue.add(startNum);

        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Long nowNum = queue.poll();
                if (nowNum == targetNum) {
                    System.out.println(count + 1);
                    return;
                }
                if (nowNum * 2 <= targetNum) queue.add(nowNum * 2);
                if ((nowNum * 10) + 1 <= targetNum) queue.add((nowNum * 10) + 1);
            }
            count++;
        }
        System.out.println(-1);
    }
}
