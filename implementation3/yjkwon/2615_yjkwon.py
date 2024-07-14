arr = [list(map(int, input().split())) for _ in range(19)]

"""
[아래, 우측, 우측아래, 좌측아래]
"""
x_arr = [1, 0, 1, 1]
y_arr = [0, 1, 1, -1]
searched = set()  # 이미 탐색된 위치를 저장하는 set


def search(c: int, x: int, y: int) -> tuple[bool, tuple[int, int] | None]:
    """
    방향에 따라 이동하며 연속되는 동일한 바둑알의 갯수를 탐색한다
    아래, 우측, 우측아래, 좌측아래 순서로 탐색한다
    :param c: 바둑알의 색깔
    :param x: 시작 x 좌표
    :param y: 시작 y 좌표
    :return:
    """
    for d in range(4):
        # d는 탐색 방향
        cnt = 1  # 동일한 바둑알의 갯수
        xp, yp = x, y  # 현재 위치의 좌표
        fxp, fyp = (
            0,
            0,
        )  # 종료 위치의 좌표 (좌측 아래로 탐색시 사용, 가장 왼쪽에 있는 바둑알은 탐색이 종료되는 시점의 좌표임)
        for i in range(6):
            xp, yp = xp + x_arr[d], yp + y_arr[d]  # 다음 지점으로 진행
            if xp == 19 or yp == 19 or arr[xp][yp] != c or (xp, yp, d) in searched:
                # 지점이 바둑판을 벗어나거나, 같은 색깔이 아니거나, 이미 탐색된 지점이면 탐색 종료
                break
            cnt += 1
            fxp, fyp = xp, yp
            searched.add((xp, yp, d))  # 지점을 탐색 방향과 함께 저장

        if cnt == 5:
            """
            동일한 바둑알의 갯수가 5인 경우
            탐색방향이 좌측 아래인 경우: 종료 위치 리턴
            그 외인 경우: 시작 위치 리턴
            """
            return True, (x, y) if d != 3 else (fxp, fyp)

    return False, None


result = []
p = []

for i in range(19):
    for j in range(19):
        v = arr[i][j]
        if not v:
            # 지점에 바둑알이 없는 경우
            continue
        r, rp = search(v, i, j)
        if r:
            result.append(v)
            p.append((rp[0] + 1, rp[1] + 1))

if len(result) == 1:
    print(result[0])
    print(p[0][0], p[0][1])
else:
    print(0)
