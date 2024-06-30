n, m = list(map(int, input().split()))
arr = [list(map(int, input().split())) for _ in range(n)]


class Point:
    x: int
    y: int
    d: int | None = None  # 안전거리

    def __init__(self, x: int, y: int) -> None:
        self.x = x
        self.y = y
        self.cal_d()

    def __lt__(self, other):
        return self.d < other.d

    def cal_d(self) -> None:
        k = 0
        while True:
            is_break = True
            k += 1
            if self.y - k >= 0:
                is_break = False
                tmp_d = self.check_left(k)
                if tmp_d:
                    self.d = tmp_d
                    break
            if self.y + k < m:
                is_break = False
                tmp_d = self.check_right(k)
                if tmp_d:
                    self.d = tmp_d
                    break
            if self.x - k >= 0:
                is_break = False
                tmp_d = self.check_top(k)
                if tmp_d:
                    self.d = tmp_d
                    break
            if self.x + k < n:
                is_break = False
                tmp_d = self.check_bottom(k)
                if tmp_d:
                    self.d = tmp_d
                    break

            if is_break:
                break

    def check_left(self, k: int) -> int:
        yp = max(0, self.y - k)
        xr = (max(0, self.x - k), min(n - 1, self.x + k))
        tmp = []

        for i in range(xr[0], xr[1] + 1):
            tmp.append(arr[i][yp])

        if 1 in tmp:
            return k
        return 0

    def check_right(self, k: int) -> int:
        yp = min(m - 1, self.y + k)
        xr = (max(0, self.x - k), min(n - 1, self.x + k))
        tmp = []

        for i in range(xr[0], xr[1] + 1):
            tmp.append(arr[i][yp])

        if 1 in tmp:
            return k
        return 0

    def check_top(self, k: int) -> int:
        xp = max(0, self.x - k)
        yr = (max(0, self.y - k), min(m - 1, self.y + k))
        tmp = []

        for i in range(yr[0], yr[1] + 1):
            tmp.append(arr[xp][i])

        if 1 in tmp:
            return k
        return 0

    def check_bottom(self, k: int) -> int:
        xp = min(n - 1, self.x + k)
        yr = (max(0, self.y - k), min(m - 1, self.y + k))
        tmp = []

        for i in range(yr[0], yr[1] + 1):
            tmp.append(arr[xp][i])

        if 1 in tmp:
            return k
        return 0


points = []

for i in range(n):
    for j in range(m):
        if arr[i][j] == 1:
            continue
        points.append(Point(i, j))

points.sort(reverse=True)
p = points[0]
print(p.d)
