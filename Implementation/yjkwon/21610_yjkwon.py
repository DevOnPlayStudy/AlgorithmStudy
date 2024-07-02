from dataclasses import dataclass

N, M = list(map(int, input().split()))
arr = [list(map(int, input().split())) for _ in range(N)]
commands = [list(map(int, input().split())) for _ in range(M)]


@dataclass
class Cloud:
    x: int
    y: int

    arr_y = (-1, -1, 0, 1, 1, 1, 0, -1)
    arr_x = (0, -1, -1, -1, 0, 1, 1, 1)

    diagonal_y = (-1, 1, 1, -1)
    diagonal_x = (-1, -1, 1, 1)

    def move_x(self, d: int, s: int) -> None:
        xp = self.x + self.arr_x[d] * s
        self.x = range(N)[xp % N]

    def move_y(self, d: int, s: int) -> None:
        yp = self.y + self.arr_y[d] * s
        self.y = range(N)[yp % N]

    def move(self, d: int, s: int) -> None:
        d -= 1
        self.move_x(d, s)
        self.move_y(d, s)

    def sprinkle(self) -> None:
        arr[self.x][self.y] += 1

    def copy_bug(self) -> None:
        for i in range(4):
            dx, dy = self.diagonal_x[i], self.diagonal_y[i]
            xp, yp = self.x + dx, self.y + dy
            if xp == -1 or xp == N or yp == -1 or yp == N:
                continue
            if arr[xp][yp] > 0:
                arr[self.x][self.y] += 1


clouds: list[Cloud] = []
clouds_tuples: list[tuple[int, int]] = []


def init_clouds():
    clouds.append(Cloud(N - 1, 0))
    clouds.append(Cloud(N - 1, 1))
    clouds.append(Cloud(N - 2, 0))
    clouds.append(Cloud(N - 2, 1))


def execute_clouds(d: int, s: int) -> None:
    for cloud in clouds:
        cloud.move(d, s)
        cloud.sprinkle()
        clouds_tuples.append((cloud.x, cloud.y))
    for cloud in clouds:
        cloud.copy_bug()


def is_cloud_exists(x: int, y: int) -> bool:
    return (x, y) in clouds_tuples


def reset_clouds() -> list[Cloud]:
    tmp: list[Cloud] = []

    for i in range(N):
        for j in range(N):
            if arr[i][j] > 1 and not is_cloud_exists(i, j):
                arr[i][j] -= 2
                tmp.append(Cloud(i, j))

    return tmp


init_clouds()


for d, s in commands:
    execute_clouds(d, s)
    clouds = reset_clouds()
    clouds_tuples = []

res = 0
for row in arr:
    res += sum(row)

print(res)
