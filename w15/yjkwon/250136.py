# 석유 시추

"""
존재 하는 모든 석유 덩어리를 dfs로 찾습니다.
석유 덩어리가 y 좌표에 걸치면 석유 값 리스트에 더합니다.
석유 값 리스트 중 최대 값을 리턴합니다.
"""

from collections import deque


def solution(land):
    n, m = len(land), len(land[0])
    l = [0] * m

    visited = set()

    def bfs(s):
        count = 0
        queue = deque([s])
        visited.add(s)
        yrange = []
        while queue:
            x, y = queue.popleft()
            yrange.append(y)
            count += 1
            for dx, dy in [(0, -1), (0, 1), (-1, 0), (1, 0)]:
                nx, ny = x + dx, y + dy
                if (
                    0 <= nx < n
                    and 0 <= ny < m
                    and land[nx][ny]
                    and (nx, ny) not in visited
                ):
                    queue.append((nx, ny))
                    visited.add((nx, ny))
        for i in range(min(yrange), max(yrange) + 1):
            l[i] += count

    for i in range(n):
        for j in range(m):
            if land[i][j] and (i, j) not in visited:
                bfs((i, j))

    answer = max(l)
    return answer
