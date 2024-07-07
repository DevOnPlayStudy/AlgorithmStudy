R, C, T = list(map(int, input().split()))
arr = [list(map(int, input().split())) for _ in range(R)]

# 상하좌우
arr_x = [-1, 1, 0, 0]
arr_y = [0, 0, -1, 1]


def copy_arr():
    """
    배열을 deepcopy하는 함수
    """
    tmp = []
    for row in arr:
        tmp.append(list(row))
    return tmp


def diffuse():
    """
    미세먼지를 확산 시키는 함수
    """
    copied_arr = copy_arr()  # deepcopy된 배열

    # 확산
    for i in range(R):
        for j in range(C):
            if arr[i][j] > 0:
                val = arr[i][j] // 5
                cnt = 0
                for k in range(4):
                    # 네 방향으로 확산
                    xp = i + arr_x[k]
                    yp = j + arr_y[k]
                    if xp == -1 or xp == R or yp == -1 or yp == C:
                        # 집을 벗어나는 경우
                        continue
                    elif arr[xp][yp] == -1:
                        # 칸이 공기청정기인 경우
                        continue
                    copied_arr[xp][yp] += val
                    cnt += 1
                # 확산이 끝난 후 해당 칸의 미세먼지가 줄어듦
                copied_arr[i][j] -= val * cnt

    return copied_arr


class Filter:
    """
    공기 청정기 클래스
    """

    top: tuple[int, int]  # 공기청정기 상단 좌표
    bottom: tuple[int, int]  # 공기청정기 하단 좌표

    def __init__(self):
        """
        공기청정기의 상단과 하단 좌표를 기록
        """
        tmp_x, tmp_y = 0, 0
        for i in range(R):
            is_break = False
            for j in range(C):
                if arr[i][j] == -1:
                    tmp_x, tmp_y = i, j
                    is_break = True
                    break
            if is_break:
                break

        self.top = (tmp_x, tmp_y)
        self.bottom = (tmp_x + 1, tmp_y)

    def clean(self, bt: str, s_list: list[int], x: int, y: int):
        """
        공기청정기 작동
        :param bt: bottom or top
        :param s_list: 방향 리스트
        :param x: 시작점의 x 좌표
        :param y: 시작점의 y 좌표
        :return:
        """
        s_index = 0  # 진행방향 인덱스
        while True:
            p = arr[x][y]  # 현재 좌표의 값
            s = s_list[s_index]  # 진행 방향
            if s == s_list[-1] and p == -1:
                """
                현재 좌표가 공기청정기 이고, 진행방향이 리스트의 마지막인 경우
                즉, 원점으로 다시 돌아온 경우
                종료
                """
                break
            xp = x + arr_x[s]  # 다음 좌표의 x 값
            yp = y + arr_y[s]  # 다음 좌표의 y 값
            if (
                xp == -1
                or xp == R
                or yp == -1
                or yp == C
                or (bt == "top" and p != -1 and xp == self.top[0] + 1)
                or (bt == "bottom" and p != -1 and xp == self.bottom[0] - 1)
            ):
                """
                좌표가 공기청정기의 작동 범위를 벗어난 경우
                진행방향을 수정함
                """
                s_index += 1
                continue
            n = arr[xp][yp]  # 다음 좌표의 값
            if n == -1 and p != -1:
                # 다음 좌표가 공기청정기인 경우
                arr[x][y] = 0
            elif p != -1:
                # 현재 좌표가 공기청정기가 아닌 경우
                arr[x][y] = n
            x, y = xp, yp  # 다음 좌표로 이동

    def clean_top(self) -> None:
        s_list = [0, 3, 1, 2]  # 상우하좌
        self.clean("top", s_list, *self.top)

    def clean_bottom(self) -> None:
        s_list = [1, 3, 0, 2]  # 하우상좌
        self.clean("bottom", s_list, *self.bottom)


f = Filter()

for _ in range(T):
    arr = diffuse()
    f.clean_top()
    f.clean_bottom()

# 집에 있는 미세먼지를 모두 더한 값
res = 0
for row in arr:
    res += sum(row)

print(res + 2)  # 집의 미세먼지를 모두 더한 값에 공기청정기(-2) 값을 추가하여 출력
