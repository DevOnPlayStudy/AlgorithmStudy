from dataclasses import dataclass
import heapq
import sys

input = sys.stdin.readline
sys.setrecursionlimit(10000)

V, E = list(map(int, input().split()))


@dataclass
class Edge:
    a: int
    b: int
    cost: int

    def __lt__(self, other):
        return self.cost < other.cost


parent = [i for i in range(V + 1)]


def find(x):
    if parent[x] != x:
        parent[x] = find(parent[x])
    return parent[x]


def union(a, b):
    a = find(a)
    b = find(b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b


edges = []

for _ in range(E):
    a, b, c = list(map(int, input().split()))
    heapq.heappush(edges, Edge(a, b, c))

answer = 0

while edges:
    edge = heapq.heappop(edges)
    if find(edge.a) != find(edge.b):
        union(edge.a, edge.b)
        answer += edge.cost

print(answer)
