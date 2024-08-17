# 1092 배
from collections import deque

N = int(input())
cranes = deque(sorted(list(map(int, input().split())), reverse=True))
M = int(input())
boxes = deque(sorted(list(map(int, input().split())), reverse=True))

if cranes[0] < boxes[0]:
    print(-1)
else:
    answer = 0
    while boxes:
        answer += 1
        for crane in cranes:
            skip = True
            for i, box in enumerate(boxes):
                if box <= crane:
                    skip = False
                    del boxes[i]
                    break
            if skip:
                break
    print(answer)
