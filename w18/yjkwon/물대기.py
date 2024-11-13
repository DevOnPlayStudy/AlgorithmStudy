import heapq
from dataclasses import dataclass
import sys

input = sys.stdin.readline
sys.setrecursionlimit(10000)

N = int(input())
W = [int(input()) for _ in range(N)]
board = [list(map(int, input().split())) for _ in range(N)]


@dataclass
class Edge:
    a: int
    b: int
    cost: int

    def __lt__(self, other):
        return self.cost < other.cost


parent = list(range(N + 1))


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

for i in range(N):
    for j in range(N):
        if i == j:
            heapq.heappush(edges, Edge(i, N, W[i]))
        else:
            heapq.heappush(edges, Edge(i, j, board[i][j]))

answer = 0

while edges:
    edge = heapq.heappop(edges)
    if find(edge.a) != find(edge.b):
        union(edge.a, edge.b)
        answer += edge.cost

print(answer)
