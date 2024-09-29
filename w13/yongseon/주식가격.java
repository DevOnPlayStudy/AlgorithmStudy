package w13.yongseon;
import java.util.*;

public class 주식가격 {
    static Stack<Integer> stack = new Stack<>();

    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        for(int i=0; i<prices.length; i++) {
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                answer[stack.peek()] = i-stack.peek();
                stack.pop();
            }

            stack.push(i);
        }

        while(!stack.isEmpty()) {
            answer[stack.peek()] = prices.length - stack.peek()-1;
            stack.pop();
        }

        return answer;
    }
}
