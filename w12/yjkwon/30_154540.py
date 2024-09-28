# 무인도 여행

"""
1. 탐색의 출발점이 될 수 있는 지점을 모두 구합니다
2. 지점이 하나도 없으면 [-1]을 리턴합니다
3. 지점마다 순회하며 값을 셉니다
"""


from collections import deque


def solution(maps):
    points = []
    for i, row in enumerate(maps):
        for j, v in enumerate(row):
            if v.isnumeric():
                points.append((i, j))
    if not points:
        return [-1]

    n, m = len(maps), len(maps[0])
    answers = []
    visited = set()
    for x, y in points:
        if (x, y) in visited:
            continue
        stack = deque([(x, y)])
        visited.add((x, y))
        answer = 0
        while stack:
            x, y = stack.pop()
            answer += int(maps[x][y])

            for dx, dy in [(0, -1), (0, 1), (-1, 0), (1, 0)]:
                xp, yp = x + dx, y + dy
                if (
                    0 <= xp < n
                    and 0 <= yp < m
                    and (xp, yp) not in visited
                    and maps[xp][yp] != "X"
                ):
                    stack.append((xp, yp))
                    visited.add((xp, yp))
        answers.append(answer)
    answers.sort()
    return answers
