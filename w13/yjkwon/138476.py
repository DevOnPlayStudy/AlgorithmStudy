# 귤 고르기

"""
1. 각 크기에 따라 귤이 몇개 있는지 저장합니다
2. 총 귤의 갯수 중에서 몇 개를 제외할지 구합니다
3. 크기별로 가장 적은 갯수를 가진 귤부터 제외합니다
"""

from collections import deque


def solution(k, tangerine):
    cases = dict()
    for t in tangerine:
        if t not in cases:
            cases[t] = 1
        else:
            cases[t] += 1
    total = len(tangerine)
    diff = total - k
    nt = deque(sorted(cases.items(), key=lambda t: t[1]))

    while diff:
        t, c = nt.popleft()
        if c > diff:
            nt.appendleft((t, c))
            break
        diff -= c

    return len(nt)
