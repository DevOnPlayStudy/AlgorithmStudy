# 1463: 1로 만들기

# BFS
# from collections import deque
#
# N = int(input())
#
# queue = deque([(0, N)])
# answer = 0
#
# while True:
#     c, x = queue.popleft()
#     if x == 1:
#         answer = c
#         break
#
#     c += 1
#
#     if x % 3 == 0:
#         queue.append((c, x // 3))
#     if x % 2 == 0:
#         queue.append((c, x // 2))
#     queue.append((c, x - 1))
#
# print(answer)

# DP
N = int(input())
memoization = [-1]

for i in range(1, N + 1):
    counts = []
    if i % 3 == 0:
        counts.append(memoization[i // 3])
    if i % 2 == 0:
        counts.append(memoization[i // 2])
    counts.append(memoization[i - 1])
    memoization.append(min(counts) + 1)

print(memoization[-1])
