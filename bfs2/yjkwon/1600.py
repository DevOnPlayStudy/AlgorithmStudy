# 1600 말이 되고픈 원숭이
from collections import deque

K = int(input())
W, H = list(map(int, input().split()))
board = [input().split() for _ in range(H)]

harr = [(-1, -2), (-2, -1), (-2, 1), (-1, 2), (1, 2), (2, 1), (2, -1), (1, -2)]
marr = [(-1, 0), (1, 0), (0, -1), (0, 1)]

visited = [[-1 for _ in range(W)] for _ in range(H)]
queue = deque([(K, 0, 0, 0)])
answer = -1

while queue:
    h, c, x, y = queue.popleft()
    if x == H - 1 and y == W - 1:
        answer = c
        break

    if h:
        for dx, dy in harr:
            xp = x + dx
            yp = y + dy
            if not (0 <= xp < H and 0 <= yp < W) or board[xp][yp] == "1":
                continue
            if visited[xp][yp] < h - 1:
                visited[xp][yp] = h - 1
                queue.append((h - 1, c + 1, xp, yp))
    for dx, dy in marr:
        xp = x + dx
        yp = y + dy
        if not (0 <= xp < H and 0 <= yp < W) or board[xp][yp] == "1":
            continue
        if visited[xp][yp] < h:
            visited[xp][yp] = h
            queue.append((h, c + 1, xp, yp))

print(answer)
