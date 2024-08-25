# 1520 내리막 길
# from collections import deque
# import sys
#
# input = sys.stdin.readline
#
# M, N = list(map(int, input().split()))
# board = [list(map(int, input().split())) for _ in range(M)]
# dp = [[0 for _ in range(N)] for _ in range(M)]
# dp[0][0] = 1
#
# li = [(0, 0)]
# queue = deque(li)
#
# while queue:
#     x, y = queue.popleft()
#     point = board[x][y]
#
#     for dx, dy in [(0, -1), (0, 1), (1, 0), (-1, 0)]:
#         xp, yp = x + dx, y + dy
#         if xp < 0 or xp >= M or yp < 0 or yp >= N or point <= board[xp][yp]:
#             continue
#         dp[xp][yp] += 1
#         queue.append((xp, yp))
#
#
# print(dp[-1][-1])
