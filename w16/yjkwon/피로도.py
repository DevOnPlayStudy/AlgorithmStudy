# 피로도

"""
모든 던전을 큐에 저장합니다.
BFS를 수행합니다.
방문한 던전을 제외한 나머지 던전들을 방문 던전 set에 포함시켜 큐에 추가합니다.
방문할 던전이 없으면 방문한 던전의 수를 정답과 비교해 갱신합니다.
"""

from collections import deque


def solution(k, dungeons):
    queue = deque()
    for i, d in enumerate(dungeons):
        queue.append((k - d[1], {i}))

    answer = -1
    while queue:
        k, visited = queue.popleft()

        finished = True
        for i, d in enumerate(dungeons):
            if i in visited:
                continue

            a, b = d
            if k >= a:
                nv = set(visited)
                nv.add(i)
                queue.append((k - b, nv))
                finished = False

        if finished:
            l = len(visited)
            if answer < l:
                answer = l

    return answer
