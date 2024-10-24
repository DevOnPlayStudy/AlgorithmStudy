import heapq
from dataclasses import dataclass
import sys

input = sys.stdin.readline

"""
친구들의 방으로 부터 다른 방까지의 최단 경로를 구합니다.
친구들의 경로를 모두 더합니다.
더한 합이 가장 작은 방을 출력합니다.
"""

T = int(input())


@dataclass
class Node:
    index: int
    distance: int

    def __lt__(self, other):
        return self.distance < other.distance


def solution():
    N, M = list(map(int, input().split()))

    graph = [list() for _ in range(N + 1)]
    for _ in range(M):
        a, b, c = list(map(int, input().split()))
        graph[a].append(Node(index=b, distance=c))
        graph[b].append(Node(index=a, distance=c))

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

    _ = input()
    friends = list(map(int, input().split()))
    answers = [0 for _ in range(N + 1)]
    for v in friends:
        ds = dijkstra(v)
        for i, d in enumerate(ds):
            answers[i] += d
    answer = 0
    answer_value = float("inf")
    for i, v in enumerate(answers):
        if answer_value > v:
            answer = i
            answer_value = v

    print(answer)


for _ in range(T):
    solution()
