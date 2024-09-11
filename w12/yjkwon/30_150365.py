# 미로 탈출 명령어

"""
백트래킹 기법을 사용합니다

1. 기존 결과보다 현재의 경로가 사전 순으로 더 뒤에 오면 탐색을 종료합니다
2. 남은 k가 없는데, 종료지점에 도달하지 못한 경우 탐색을 종료합니다
3. 남은 거리가 k보다 크면 탐색을 종료합니다
4. 남은 거리가 짝수인데 k가 홀수이면 탐색을 종료합니다
"""

import sys

sys.setrecursionlimit(5000)


def solution(n, m, x, y, r, c, k):
    xarr = [1, 0, 0, -1]
    yarr = [0, -1, 1, 0]
    commands = "dlru"
    result = ["z" * k]

    def backtrack(x, y, k, path):
        if result[0] <= path:
            return
        if x == r and y == c and k == 0:
            result[0] = path
            return
        if k == 0:
            return
        remain = abs(x - r) + abs(y - c)
        if remain > k:
            return
        if not (remain % 2) and k % 2:
            return

        for i in range(4):
            nx, ny = x + xarr[i], y + yarr[i]
            if 1 <= nx <= n and 1 <= ny <= m:
                backtrack(nx, ny, k - 1, path + commands[i])

    backtrack(x, y, k, "")
    return result[0] if "z" not in result[0] else "impossible"
