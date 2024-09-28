# 전력망을 둘로 나누기

"""
1. 주어진 연결을 이용해 트리를 만듭니다.
2. 각 연결별로 순회하며 연결 종단의 두 노드를 시작으로 연결된 노드의 갯수를 셉니다
"""

from collections import deque


class Node:
    def __init__(self):
        self.connected = list()


def solution(n, wires):
    tree = dict()

    def extend_tree(x, y):
        if x not in tree:
            tree[x] = Node()
        tree[x].connected.append(y)

    for x, y in wires:
        extend_tree(x, y)
        extend_tree(y, x)

    def count_nodes(x, y):
        visited = set([x, y])
        queue = deque([x])
        count = 0
        while queue:
            v = queue.popleft()
            visited.add(v)
            count += 1
            queue.extend([n for n in tree[v].connected if n not in visited])
        return count

    answers = []
    for x, y in wires:
        answers.append((count_nodes(x, y), count_nodes(y, x)))

    answers.sort(key=lambda x: abs(x[0] - x[1]))
    return abs(answers[0][0] - answers[0][1])
