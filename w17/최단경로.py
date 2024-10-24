import heapq
from dataclasses import dataclass
import sys

input = sys.stdin.readline

V, E = list(map(int, input().split()))
start = int(input())


@dataclass
class Node:
    index: int
    distance: int

    def __lt__(self, other):
        return self.distance < other.distance


graph = [list() for _ in range(V + 1)]
d = [float("inf") for _ in range(V + 1)]
d[start] = 0

for _ in range(E):
    u, v, w = list(map(int, input().split()))
    graph[u].append(Node(index=v, distance=w))


queue = []
heapq.heappush(queue, Node(index=start, distance=0))

while queue:
    current_node = heapq.heappop(queue)

    for next_node in graph[current_node.index]:
        new_distance = current_node.distance + next_node.distance
        if d[next_node.index] > new_distance:
            d[next_node.index] = new_distance
            heapq.heappush(queue, Node(index=next_node.index, distance=new_distance))


for distance in d[1:]:
    if distance == float("inf"):
        print("INF")
    else:
        print(distance)
