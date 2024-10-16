# 당구 연습

"""
상하좌우
상 : x -> 1, y -> 2n - y
하 : x -> 1, y -> -1
좌 : x -> -1, y -> 1
우 : x -> 2m - x, y -> 1

목표 공을 시작 공을 보낼 방향에 위치한 면의 반대 방향으로 보내고
피타고라스 정리를 이용하여 진행 거리를 계산

시작 공과 목표 공이 일직선 상에 있으면 목표 공의 방향으로는 공을 보내지 않음
"""


def cal(a, b):
    return abs(a[0] - b[0]) ** 2 + abs(a[1] - b[1]) ** 2


def solution(m, n, startX, startY, balls):
    answer = []
    s = (startX, startY)

    for b in balls:
        d = []
        if startX != b[0]:
            d.append(cal(s, (b[0], 2 * n - b[1])))
            d.append(cal(s, (b[0], -b[1])))
        elif startY > b[1]:
            d.append(cal(s, (b[0], 2 * n - b[1])))
        elif startY < b[1]:
            d.append(cal(s, (b[0], -b[1])))
        if startY != b[1]:
            d.append(cal(s, (-b[0], b[1])))
            d.append(cal(s, (2 * m - b[0], b[1])))
        elif startX < b[0]:
            d.append(cal(s, (-b[0], b[1])))
        elif startX > b[0]:
            d.append(cal(s, (2 * m - b[0], b[1])))
        answer.append(min(d))

    return answer
