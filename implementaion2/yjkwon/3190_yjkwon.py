from typing import Deque
from collections import deque

N = int(input())
K = int(input())
apples = {tuple(map(int, input().split())) for _ in range(K)}
L = int(input())
commands = [list(input().split()) for _ in range(L)]
commands = [(int(t), c) for t, c in commands]


def create_arr() -> list[list[int]]:
    """
    보드를 생성하는 함수
    """
    tmp = []
    for i in range(N):
        row = []
        for j in range(N):
            val = 0
            if (i + 1, j + 1) in apples:
                val = 1
            row.append(val)
        tmp.append(row)
    return tmp


arr = create_arr()


class Snake:
    body: Deque[tuple[int, int]]

    def __init__(self) -> None:
        """
        body 초기화
        """
        self.body = deque()
        self.body.append((0, 0))

    def move(self, dx: int, dy: int) -> int:
        """
        뱀이 이동하는 함수
        :param dx: x축 진행 값
        :param dy: y축 진행 값
        :return: 진행결과 값, 진행 실패시 -1, 진행 성공시 0
        """
        xp, yp = self.body[0][0] + dx, self.body[0][1] + dy  # 다음 좌표값

        if xp == -1 or xp == N or yp == -1 or yp == N or (xp, yp) in self.body:
            # 보드를 벗어나거나, 뱀 자신과 부딪히면 진행 실패로 -1 리턴
            return -1

        self.body.appendleft((xp, yp))  # body에 머리 추가

        n = arr[xp][yp]  # 다음 좌표의 값

        if not n:
            # 좌표가 0이면 body에서 꼬리를 제거
            self.body.pop()
        else:
            # 좌표가 1이면, 즉 사과가 있으면 좌표를 0으로 변경
            arr[xp][yp] = 0

        return 0  # 정상적인 진행


snake = Snake()
# 좌상우하
x_arr = [0, -1, 0, 1]
y_arr = [-1, 0, 1, 0]
d = 2
next_command = 0
time = 0

while True:
    if next_command != -1 and commands[next_command][0] == time:
        """
        다음 커맨드가 입력되는 시간에 도래한 경우
        """
        command = commands[next_command][1]  # 커맨드
        next_command += 1  # 다음 커맨드를 지정
        if next_command == len(commands):
            # 커맨드가 더이상 없는 경우
            next_command = -1

        if command == "L":
            # 좌측으로 90도 회전
            d -= 1
            if d == -1:
                d = 3
        elif command == "D":
            # 우측으로 90도 회전
            d += 1
            if d == 4:
                d = 0

    time += 1

    result = snake.move(x_arr[d], y_arr[d])

    if result == -1:
        # 진행에 실패한 경우 종료
        break

print(time)
