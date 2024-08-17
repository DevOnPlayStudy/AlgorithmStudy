# 23317 구슬 굴리기

"""
f(0) -> 1
f(1) -> 2
f(2) -> 4
f(3) -> 8
f(4) -> 16
f(5) -> 32
"""

# n = int(input())
# matrix = [[1] * (i + 1) for i in range(n)]
#
# for _ in range(int(input())):
#     x, y = list(map(int, input().split()))
#     if x > 0:
#         tmp = []
#         for i in range(x):
#             if i == y or i == y - 1:
#                 tmp.append(1)
#             else:
#                 tmp.append(0)
#         matrix[x - 1] = tmp
#     if x < n - 1:
#         tmp = []
#         for i in range(x + 2):
#             if i == y or i == y + 1:
#                 tmp.append(1)
#             else:
#                 tmp.append(0)
#         matrix[x + 1] = tmp
#     tmp = []
#     for i in range(x + 1):
#         if i == y:
#             tmp.append(1)
#         else:
#             tmp.append(0)
#     matrix[x] = tmp
#
# for row in matrix:
#     print(row)
#
# rows = len(matrix)
# cols = len(matrix[-1])
#
# dp = [[0 for _ in range(cols)] for _ in range(rows)]
#
# dp[0] = matrix[0]
#
# for i in range(1, rows):
#     for j in range(len(matrix[i])):
#         if matrix[i][j] == 0:
#             dp[i][j] = 0
#         else:
#             left = dp[i - 1][j - 1] if j > 0 else 0
#             right = dp[i - 1][j] if j < len(matrix[i - 1]) else 0
#             dp[i][j] = left + right
#
# print(sum(dp[-1]))
