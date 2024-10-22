package w13.yongseon;

import java.util.*;

public class 롤케이크자르기 {
    public int solution(int[] topping) {
        int answer = 0;

        // 각 토핑의 개수를 관리하는 맵 생성
        Map<Integer, Integer> toppingCount = new HashMap<>();
        Set<Integer> currentTopping = new HashSet<>();

        // 전체 토핑의 개수를 초기화
        for (int t : topping) {
            toppingCount.put(t, toppingCount.getOrDefault(t, 0) + 1);
        }

        // 토핑을 순회하면서 현재 세트와 남은 토핑 개수를 업데이트
        for (int i = 0; i < topping.length; i++) {
            int t = topping[i];

            // 현재 토핑에 추가
            currentTopping.add(t);

            // 남은 토핑에서 해당 토핑의 개수를 감소
            toppingCount.put(t, toppingCount.get(t) - 1);
            if (toppingCount.get(t) == 0) {
                toppingCount.remove(t);
            }

            // 남은 토핑의 종류와 현재 토핑의 종류가 같으면 카운트 증가
            if (currentTopping.size() == toppingCount.size()) {
                answer++;
            }
        }

        return answer;
    }
}
