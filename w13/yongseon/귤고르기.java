package w13.yongseon;

import java.util.*;

public class 귤고르기 {
    static Map<Integer, Integer> tangerineMap = new HashMap();

    public int solution(int k, int[] tangerine) {
        int answer = 0;

        for(int t : tangerine) {
            tangerineMap.put(t, tangerineMap.getOrDefault(t, 0) + 1);
        }

        List<Integer> sortedKeys = new ArrayList<>(tangerineMap.keySet());
        sortedKeys.sort((size1, size2) -> tangerineMap.get(size2) - tangerineMap.get(size1));

        for(Integer key: sortedKeys) {
            k -= tangerineMap.get(key);

            answer++;

            if(k <= 0) {
                break;
            }
        }

        return answer;
    }
}
