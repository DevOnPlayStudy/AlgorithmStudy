package w16.yongseon;

import java.util.*;

public class 단어변환 {
    public class Node {
        String word;
        Integer step;

        public Node(String word, Integer step) {
            this.word = word;
            this.step = step;
        }
    }

    private static boolean[] visited;
    private static Queue<Node> q = new LinkedList<>();

    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        q.add(new Node(begin, 0));

        while (!q.isEmpty()) {
            Node currentNode = q.poll();

            if (currentNode.word.equals(target)) {
                return currentNode.step;
            }

            for (int i = 0; i < words.length; i++) {
                int count = 0;

                if (!visited[i] && isTransform(currentNode.word, words[i])) {
                    visited[i] = true;
                    q.add(new Node(words[i], currentNode.step + 1));
                }
            }
        }

        return 0;
    }

    private boolean isTransform(String word, String targetWord) {
        int diffWord = 0;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != targetWord.charAt(i)) {
                diffWord++;

                if (diffWord > 1) return false;
            }
        }

        return true;
    }

}
