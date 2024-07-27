package bfs1.junseongkim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/9205
public class 맥주마시면서걸어가기 {
    static int N;
    static Node startNode, endNode;
    static List<Node> storeList; // 편의점 node 저장소

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for (int i = 0; i < T; i++) {
            storeList = new ArrayList<>();
            N = Integer.parseInt(br.readLine()); // 편의점 개수

            for (int j = 0; j < N + 2; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                if (j == 0) {
                    // 출발 위치
                    int startCol = Integer.parseInt(st.nextToken());
                    int startRow = Integer.parseInt(st.nextToken());
                    startNode = new Node(startRow, startCol);
                } else if (j == N + 1) {
                    // 목적지 위치
                    int endCol = Integer.parseInt(st.nextToken());
                    int endRow = Integer.parseInt(st.nextToken());
                    endNode = new Node(endRow, endCol);
                }else{
                    // 중간 편의점 위치
                    int midCol = Integer.parseInt(st.nextToken());
                    int midRow = Integer.parseInt(st.nextToken());
                    storeList.add(new Node(midRow, midCol));
                }
            }

            bfs();
        }
    }

    static void bfs() {
        Queue<Node> queue = new LinkedList<>(); // 이동 좌표를 큐에 저장
        boolean[] visited = new boolean[N]; // 편의점 방문처리를 위한 boolean 배열
        queue.add(startNode); // 시작위치를 큐에 저장

        while (!queue.isEmpty()) {
            // 현재 위치
            Node nowNode = queue.poll();

            // 현재 위치에서 도착위치까지 1000미터 이내일 경우 happy를 출력 후 while문 종료
            if (checkDistance(nowNode, endNode) <= 1000) {
                System.out.println("happy");
                return;
            }

            // 편의점 방문
            for (int i = 0; i < N; i++) { // 편의점 개수만큼 for문 수행
                // i 번째 편의점에 방문하지 않았는지 확인
                if (!visited[i]) {
                    Node nowStore = storeList.get(i); // 편의점의 좌표를 꺼낸다.

                    // 현재 위치에서 다음 편의점 위치까지 1000미터 이내라면 편의점 방문 가능
                    if (checkDistance(nowNode, nowStore) <= 1000) {
                        visited[i] = true;
                        queue.add(nowStore); // 현재 편의점 위치가 다음 상근이의 위치가 된다.
                    }
                }
            }

        }
        // 도착하지 못했다면 say 출력
        System.out.println("sad");
    }

    static int checkDistance(Node nowNode, Node nextNode) {
        int nowRow = nowNode.row;
        int nowCol = nowNode.col;

        int nextRow = nextNode.row;
        int nextCol = nextNode.col;

        // 현재 노트에서 row, col 과 목적지 row, col을 뺀 거리를 반환
        return Math.abs(nowRow - nextRow) + Math.abs(nowCol - nextCol);
    }

    static class Node {
        int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
