import heapq
from dataclasses import dataclass
import sys

input = sys.stdin.readline
sys.setrecursionlimit(10000)

N, M, t = list(map(int, input().split()))


@dataclass
class Edge:
    a: int
    b: int
    cost: int

    def __lt__(self, other):
        return self.cost < other.cost


parents = list(range(N + 1))


def find(x):
    if parents[x] != x:
        parents[x] = find(parents[x])

    return parents[x]


def union(a, b):
    a = find(a)
    b = find(b)

    if a < b:
        parents[b] = a
    else:
        parents[a] = b


edges = []
edges_from_first = []


for _ in range(M):
    a, b, c = list(map(int, input().split()))
    edge = Edge(a, b, c)
    if a == 1 or b == 1:
        heapq.heappush(edges_from_first, edge)
    else:
        heapq.heappush(edges, edge)

from_first = heapq.heappop(edges_from_first)
union(from_first.a, from_first.b)
answer = from_first.cost
for edge in edges_from_first:
    heapq.heappush(edges, edge)
updated = 1

while edges:
    edge = heapq.heappop(edges)

    if find(edge.a) != find(edge.b):
        union(edge.a, edge.b)
        answer += edge.cost + (updated * t)
        updated += 1

print(answer)
