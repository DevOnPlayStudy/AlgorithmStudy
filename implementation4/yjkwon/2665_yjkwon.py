# 미로만들기
n = int(input())
arr = [list(map(int, list(input()))) for _ in range(n)]

# 이동방향 상하좌우
x_arr = [-1, 1, 0, 0]
y_arr = [0, 0, -1, 1]

queue = [(0, 0, 0)]  # (검은 방의 수, x좌표, y좌표)
count = None
visited = {(0, 0)}

while queue:
    queue.sort(key=lambda tp: tp[0])  # 검은 방의 수가 적은 순으로 정렬
    c, x, y = queue.pop(0)
    if x == n - 1 and y == n - 1:
        # 끝방에 도달하면 종료
        count = c
        break

    for i in range(4):
        # 상하좌우 진행
        dx, dy = x_arr[i], y_arr[i]
        xp, yp = x + dx, y + dy
        if xp == -1 or xp == n or yp == -1 or yp == n:
            continue
        if (xp, yp) not in visited:
            cp = c + int(not bool(arr[xp][yp]))  # 검은 방인 경우 +1
            queue.append((cp, xp, yp))
            visited.add((xp, yp))


print(count)
