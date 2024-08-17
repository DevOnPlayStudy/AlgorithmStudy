# 1890 점프

N = int(input())
board = [list(map(int, input().split())) for _ in range(N)]
memoization = [[0 for _ in range(N)] for _ in range(N)]
memoization[0][0] = 1

for i in range(N):
    for j in range(N):
        point = board[i][j]
        count = memoization[i][j]
        if count == 0 or (i == N - 1 and j == N - 1):
            continue
        ip = i + point
        jp = j + point
        if ip < N:
            memoization[ip][j] += count
        if jp < N:
            memoization[i][jp] += count

print(memoization[N - 1][N - 1])
