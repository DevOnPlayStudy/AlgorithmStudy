# 3064 Minesweeper
for _ in range(int(input())):
    N = int(input())
    board = [list(input()) for _ in range(N)]

    def fill(x, y, darr):
        counts = int(board[x][y])
        for dx, dy in darr:
            xp, yp = x + dx, y + dy
            if xp < 1 or xp > N - 2 or yp < 1 or yp > N - 2 or board[xp][yp] == "x":
                continue
            if counts:
                counts -= 1
                board[xp][yp] = "*"
            else:
                board[xp][yp] = "x"

    for i in range(N):
        fill(0, i, [(1, -1), (1, 0), (1, 1)])
        fill(N - 1, i, [(-1, -1), (-1, 0), (-1, 1)])
        fill(i, 0, [(-1, 1), (0, 1), (1, 1)])
        fill(i, N - 1, [(-1, -1), (0, -1), (1, -1)])

    for i in range(2, N - 2):
        for j in range(2, N - 2):
            board[i][j] = "*"

    answer = 0
    for row in board:
        answer += row.count("*")
    print(answer)
