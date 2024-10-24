package w16.yongseon;

import java.util.*;

public class N으로표현 {
    List<Set<Integer>> dp = new ArrayList<>();

    public int solution(int N, int number) {
        int answer = 0;

        for (int i = 0; i < 9; i++) {
            dp.add(new HashSet<>());
        }

        for (int i = 1; i < 9; i++) {
            Set<Integer> currentSet = dp.get(i);

            int repeatedNumber = Integer.parseInt(String.valueOf(N).repeat(i));
            currentSet.add(repeatedNumber);

            for (int j = 1; j < i; j++) {
                Set<Integer> set1 = dp.get(j);
                Set<Integer> set2 = dp.get(i - j);

                for (int num1 : set1) {
                    for (int num2 : set2) {
                        currentSet.add(num1 + num2);
                        currentSet.add(num1 - num2);
                        currentSet.add(num1 * num2);

                        if (num2 != 0) {
                            currentSet.add(num1 / num2);
                        }
                    }
                }
            }

            // 목표 숫자가 현재 집합에 있는지 확인
            if (currentSet.contains(number)) {
                return i;
            }
        }

        return -1;
    }

}
