# 맥주 마시면서 걸어가기
t = int(input())


def solution():
    n = int(input())
    arr = [tuple(map(int, input().split())) for _ in range(n + 2)]
    home, cvs, pent = arr[0], arr[1:], arr[-1]

    queue = [home]
    answer = "sad"
    visited = set(queue)

    while queue:
        x, y = queue.pop()
        if pent[0] == x and pent[1] == y:
            # 목적지에 도착하면 종료
            answer = "happy"
            break

        for xp, yp in cvs:
            if abs(xp - x) + abs(yp - y) <= 1000 and (xp, yp) not in visited:
                # 현재 좌표와 다음 좌표사이의 거리가 1000 이하인 경우 추가
                visited.add((xp, yp))
                queue.append((xp, yp))

    print(answer)


for _ in range(t):
    solution()
