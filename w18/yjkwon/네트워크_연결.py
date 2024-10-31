import heapq
from dataclasses import dataclass
import sys

input = sys.stdin.readline
sys.setrecursionlimit(10000)

N = int(input())
M = int(input())


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

for _ in range(M):
    a, b, c = list(map(int, input().split()))
    heapq.heappush(edges, Edge(a, b, c))

answer = 0

while edges:
    edge = heapq.heappop(edges)

    if find(edge.a) != find(edge.b):
        union(edge.a, edge.b)
        answer += edge.cost


print(answer)
