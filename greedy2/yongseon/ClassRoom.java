package greedy2.yongseon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class ClassRoom {
    static class Lecture implements Comparable<Lecture> {
        int num;
        int startTime;
        int endTime;

        public Lecture(int num, int startTime, int endTime) {
            this.num = num;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Lecture o) {
            return this.startTime - o.startTime;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Lecture[] lectures = new Lecture[n];

        for (int i = 0; i < n; i++) {
            int[] lecture = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int num = lecture[0];
            int startTime = lecture[1];
            int endTime = lecture[2];

            lectures[i] = new Lecture(num, startTime, endTime);
        }

        Arrays.sort(lectures);

        // 2. 우선순위 큐를 사용하여 종료 시간 관리
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(lectures[0].endTime);

        for (int i = 1; i < n; i++) {
            // 만약 현재 강의의 시작 시간이 가장 빨리 끝나는 강의의 종료 시간보다 늦다면
            // 기존의 강의실을 재사용할 수 있다.
            if (lectures[i].startTime >= pq.peek()) {
                pq.poll(); // 강의실을 비워줌
            }
            // 현재 강의를 새로 추가
            pq.add(lectures[i].endTime);
        }

        // 3. 필요한 강의실의 최소 개수는 우선순위 큐의 크기
        System.out.println(pq.size());
    }
}