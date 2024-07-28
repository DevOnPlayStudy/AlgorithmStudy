package implementation4.yongseon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MakeMaze {
    private static int[][] grid;
    private static boolean[][] visited;
    private static final Deque<Node> queue = new ArrayDeque<>();
    private static int[] dx = new int[] {0, 0, -1, 1};
    private static int[] dy = new int[] {1, -1, 0, 0};

    public static class Node {
        private int x;
        private int y;
        private int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        // 맵의 구조와 총 방문 여부 배열을 세팅하는 코드
        setGrid(br, n);

        // 최소 방문 거리를 구하는 메서드
        System.out.println(bfs());
    }

    private static int bfs() {
        // 가장 처음 위치에 시작하는 노드를 넣는다.
        queue.add(new Node(0 , 0, 0));

        // 방문 체크
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            // 현재 노드를 꺼낸다.
            Node currentNode = queue.poll();

            // 현재 노드가 도착 위치에 도달하면 블랙룸을 화이트룸으로 바꿔야하는 비용 리턴
            if (currentNode.x == grid[0].length-1 && currentNode.y == grid.length-1) {
                return currentNode.cost;
            }

            // 상하좌우로 이동하면서 노드 추가
            for (int i = 0; i < 4; i++) {
                int nx = currentNode.x + dx[i];
                int ny = currentNode.y + dy[i];

                // 방문한 노드가 아니고 맵의 구조를 벗어나지 않은 노드들을 순환
                if (validation(nx, ny) && !visited[ny][nx]) {
                    // 다음 노드 방문 처리
                    visited[ny][nx] = true;

                    // 다음방이 화이트룸이면 코스트를 추가하지 않고 앞에 추가하고 블랙룸이라면 코스트롤 증가시키고 뒤에 넣는다.
                    if (grid[ny][nx] == 1) {
                        queue.addFirst(new Node(nx, ny, currentNode.cost));
                    } else {
                        queue.addLast(new Node(nx, ny, currentNode.cost+1));
                    }
                }
            }
        }

        return -1;
    }

    private static void setGrid(BufferedReader br, int n) throws IOException{
        grid = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            int[] cols = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < cols.length; j++) {
                grid[i][j] = cols[j];
            }
        }
    }

    private static boolean validation(int x, int y) {
        return x >= 0 && x < grid[0].length && y >= 0 && y < grid.length;
    }
}
