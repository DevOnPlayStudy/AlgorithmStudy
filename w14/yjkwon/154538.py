# 숫자 변환하기
from collections import deque


def solution(x, y, n):
    queue = deque([(0, x)])
    visited = set([x])
    while queue:
        c, v = queue.popleft()
        if v == y:
            return c
        for vp in [v + n, v * 2, v * 3]:
            if vp <= y and vp not in visited:
                queue.append((c + 1, vp))
                visited.add(vp)
    return -1
