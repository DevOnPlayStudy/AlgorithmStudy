package w16.yongseon;

public class 피로도 {
    private static boolean[] visited;
    private static int max = 0;

    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        visited = new boolean[dungeons.length];
        dfs(k, dungeons, 0, 0);
        return max;
    }

    private static void dfs(int k, int[][] dungeons, int index, int count) {
        if (index == dungeons.length) {
            max = Math.max(max, count);
            return;
        }

        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;

            if (k >= dungeons[i][0]) {
                dfs(k - dungeons[i][1], dungeons, index + 1, count + 1);
            } else {
                dfs(k, dungeons, index + 1, count);
            }

            visited[i] = false;
        }

        return;
    }

}
