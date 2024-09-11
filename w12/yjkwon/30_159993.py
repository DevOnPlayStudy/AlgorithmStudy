# 미로 탈출

"""
시작지점 -> 레버
레버 -> 종료지점
순서로 bfs 탐색을 하며 소요시간을 구합니다
"""

from collections import deque


def solution(maps):
    start = ()
    lever = ()
    exit = ()
    for i, row in enumerate(maps):
        for j, v in enumerate(row):
            if v == "S":
                start = (i, j)
            elif v == "L":
                lever = (i, j)
            elif v == "E":
                exit = (i, j)

    def bfs(n, m, start, goal):
        queue = deque([(0, *start)])
        visited = set([start])
        while queue:
            c, x, y = queue.popleft()
            if (x, y) == goal:
                return c

            for dx, dy in [(0, -1), (0, 1), (-1, 0), (1, 0)]:
                nx, ny = x + dx, y + dy
                if (
                    0 <= nx < n
                    and 0 <= ny < m
                    and maps[nx][ny] != "X"
                    and (nx, ny) not in visited
                ):
                    queue.append((c + 1, nx, ny))
                    visited.add((nx, ny))
        return -1

    n, m = len(maps), len(maps[0])
    c1 = bfs(n, m, start, lever)
    c2 = bfs(n, m, lever, exit)
    if c1 == -1 or c2 == -1:
        return -1
    return c1 + c2
