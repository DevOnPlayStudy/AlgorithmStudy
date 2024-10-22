# Snakes and Ladders

"""
2차원 배열 board를 1차원으로 만듭니다.
    - 짝수열은 정방향으로
    - 홀수열은 역방향으로
    - 새 배열에 추가합니다
BFS를 이용해 가장 빠른 경로를 구합니다.
"""

from typing import List


class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        b = [0]
        for i, row in enumerate(reversed(board)):
            if not i % 2:
                b.extend(row)
            else:
                b.extend(reversed(row))

        q = [(0, 1)]
        visited = set([1])
        goal = len(b) - 1

        while q:
            c, n = q.pop(0)
            if n == goal:
                return c
            for i in range(n + 1, min(n + 6, goal) + 1):
                if b[i] == -1 and i in visited:
                    continue
                elif b[i] == -1:
                    q.append((c + 1, i))
                    visited.add(i)
                elif b[i] not in visited:
                    q.append((c + 1, b[i]))
                    visited.add(b[i])

        return -1
