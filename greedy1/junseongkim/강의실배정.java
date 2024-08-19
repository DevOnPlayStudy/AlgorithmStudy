package greedy1.junseongkim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 강의실배정 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ClassInfo[] classInfos;
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        classInfos = new ClassInfo[N];

        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split(" ");
            classInfos[i] = new ClassInfo(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        }

        Arrays.sort(classInfos);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(classInfos[0].getEnd());

        for (int i = 1; i < N; i++) {
            // 가장 빨리 끝나느 강의 종료시간보다 현재 강의의 시작 시간이 크거나 같으면 해당 강의실 사용가능
            if (pq.peek() <= classInfos[i].getStart()) {
                pq.poll();
            }
            // 새로운 강의를 해당 강의실에 배정하거나 새로운 강의실 필요한 경우
            pq.add(classInfos[i].getEnd());
        }
        System.out.println(pq.size());
    }
    
    static class ClassInfo implements Comparable<ClassInfo>{
        int start;
        int end;

        public ClassInfo(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return this.start;
        }
        
        public int getEnd() {
            return this.end;
        }

        @Override
        public int compareTo(ClassInfo classInfo) {
            if (this.getStart() == classInfo.getStart()) {
                return this.getEnd() - classInfo.getEnd();
            }else{
                return this.getStart() - classInfo.getStart();

            }
        }
    }
}
