import heapq
from dataclasses import dataclass
import sys

input = sys.stdin.readline

"""
1 -> v1 -> v2 -> N,
1 -> v2 -> v1 -> N
둘 중 최단 경로를 출력합니다.
"""

N, E = list(map(int, input().split()))


@dataclass
class Node:
    index: int
    distance: int

    def __lt__(self, other):
        return self.distance < other.distance


graph = [list() for _ in range(N + 1)]

for _ in range(E):
    a, b, c = list(map(int, input().split()))
    graph[a].append(Node(index=b, distance=c))
    graph[b].append(Node(index=a, distance=c))


v1, v2 = list(map(int, input().split()))

"""
min(
1 -> v1 -> v2 -> N,
1 -> v2 -> v1 -> N
)
"""


def dijkstra(start: int):
    d = [float("inf") for _ in range(N + 1)]
    d[start] = 0

    queue = []
    heapq.heappush(queue, Node(index=start, distance=0))

    while queue:
        current_node = heapq.heappop(queue)
        for next_node in graph[current_node.index]:
            new_distance = current_node.distance + next_node.distance
            if d[next_node.index] > new_distance:
                d[next_node.index] = new_distance
                heapq.heappush(
                    queue, Node(index=next_node.index, distance=new_distance)
                )

    return d


from_1 = dijkstra(1)
if v1 != 1:
    from_v1 = dijkstra(v1)
else:
    from_v1 = from_1
if v2 != N:
    from_v2 = dijkstra(v2)
else:
    from_v2 = [0] * (N + 1)

answers = []
answers.append(from_1[v1] + from_v1[v2] + from_v2[N])
answers.append(from_1[v2] + from_v2[v1] + from_v1[N])

answer = min(answers)
if answer >= float("inf"):
    print(-1)
else:
    print(answer)
