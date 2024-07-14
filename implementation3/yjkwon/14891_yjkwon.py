# 톱니바퀴
from typing import Deque
from collections import deque

arr = [list(map(int, list(input()))) for _ in range(4)]
K = int(input())
commands = [list(map(int, input().split())) for _ in range(K)]


class Gear:
    arr: Deque

    def __init__(self, li: list[int]) -> None:
        self.arr = deque(li)

    def cr(self):
        # clockwise rotation
        self.arr.appendleft(self.arr.pop())

    def ccr(self):
        # counter-clockwise rotation
        self.arr.append(self.arr.popleft())

    @property
    def left(self) -> int:
        return self.arr[6]

    @property
    def right(self) -> int:
        return self.arr[2]


gears = [Gear(li) for li in arr]

for no, d in commands:
    no -= 1
    _commands = [(no, d)]
    _dl = d
    _dr = d

    """
    시작 기어로 부터 왼쪽 방향으로 진행하며 기어가 움직여야 하는지 확인
    """
    if no != 0:
        for i, gear in enumerate(reversed(gears[:no])):
            i = no - 1 - i
            if gear.right != gears[i + 1].left:
                _dl = -1 if _dl == 1 else 1
                _commands.append((i, _dl))
            else:
                break

    """
    시작 기어로 부터 오른쪽 방향으로 진행하며 기어가 움직어야 하는지 확인
    """
    if no != 3:
        for j, gear in enumerate(gears[no + 1 :]):
            j = no + 1 + j
            if gear.left != gears[j - 1].right:
                _dr = -1 if _dr == 1 else 1
                _commands.append((j, _dr))
            else:
                break

    for k, dp in _commands:
        match dp:
            case 1:
                gears[k].cr()
            case -1:
                gears[k].ccr()

answer = 0

answer += gears[0].arr[0] * 1
answer += gears[1].arr[0] * 2
answer += gears[2].arr[0] * 4
answer += gears[3].arr[0] * 8

print(answer)
