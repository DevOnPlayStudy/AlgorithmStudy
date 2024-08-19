package greedy1.junseongkim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 회의실배정 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static MeetingInfo[] meetingInfos;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        meetingInfos = new MeetingInfo[N];
        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split(" ");
            meetingInfos[i] = new MeetingInfo(Long.parseLong(split[0]), Long.parseLong(split[1]));
        }

        Arrays.sort(meetingInfos);

        int count = 0;
        long prevEndTime = 0;

        for (int i = 0; i < N; i++) {
            if (prevEndTime <= meetingInfos[i].getStart()) {
                prevEndTime = meetingInfos[i].getEnd();
                count++;
            }
        }

        System.out.println(count);

    }

    static class MeetingInfo implements Comparable<MeetingInfo> {
        Long start;
        Long end;

        public MeetingInfo(Long start, Long end) {
            this.start = start;
            this.end = end;
        }

        public Long getStart() {
            return this.start;
        }

        public Long getEnd() {
            return this.end;
        }


        @Override
        public int compareTo(MeetingInfo o) {
            if (this.getEnd() == o.getEnd()) {
                return (int) (this.getStart() - o.getStart());
            } else {
                return (int) (this.getEnd() - o.getEnd());
            }
        }
    }
}
