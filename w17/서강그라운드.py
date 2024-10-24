import heapq
from dataclasses import dataclass
import sys

input = sys.stdin.readline

"""
각 지점에서 출발하여 다른 지점까지 도착하는 최단경로를 모두 구합니다.
다른 지점까지의 거리가 수색범위 보다 같거나 짧으면 모두 더합니다.
더한 값들 중 최대값을 출력합니다.
"""

n, m, r = list(map(int, input().split()))
items = list(map(int, input().split()))


@dataclass
class Node:
    index: int
    distance: int

    def __lt__(self, other):
        return self.distance < other.distance


graph = [list() for _ in range(n + 1)]

for _ in range(r):
    a, b, l = list(map(int, input().split()))
    graph[a].append(Node(index=b, distance=l))
    graph[b].append(Node(index=a, distance=l))

answers = []

for i in range(1, n + 1):
    queue = []
    d = [float("inf") for _ in range(n + 1)]
    d[i] = 0
    heapq.heappush(queue, Node(index=i, distance=0))

    while queue:
        current_node = heapq.heappop(queue)
        for next_node in graph[current_node.index]:
            new_distance = current_node.distance + next_node.distance
            if d[next_node.index] > new_distance:
                d[next_node.index] = new_distance
                queue.append(Node(index=next_node.index, distance=new_distance))

    answer = 0
    for j, dist in enumerate(d[1:]):
        if dist <= m:
            answer += items[j]

    answers.append(answer)

print(max(answers))
