N, L = list(map(int, input().split()))
arr = [list(map(int, input().split())) for _ in range(N)]

# 검사 진행 방향 (우, 하)
x_arr = [0, 1]
y_arr = [1, 0]


def check(x: int, y: int, d: int) -> int:
    """
    행 또는 열을 검사하는 함수
    :param x: 출발 지점의 x 좌표
    :param y: 출발 지점의 y 좌표
    :param d: 진행 방향 index
    :return: 검사 성공 여부
    """
    cnt = 1  # 같은 값 카운트
    prev = arr[x][y]  # 직전 좌표의 값
    ramps = set()  # 경사로가 있는 좌표
    p = 0  # 증가 혹은 감소 추세, 0: 중립, 1: 증가: 2: 감소

    while True:
        x, y = x + x_arr[d], y + y_arr[d]
        if x == N or y == N:
            """
            좌표가 끝에 도달한 경우

            추세가 감소인데 카운트가 경사로의 길이보다 짧으면 return 0
            """
            return 1 if p != 2 or cnt >= L else 0
        current = arr[x][y]  # 현재 좌표의 값

        if current == prev:
            """
            현재 좌표가 직전 좌표와 같은 경우
            """
            cnt += 1
            if p == 2 and cnt == L:
                """
                추세가 감소인 경우 카운트가 경사로의 길이와 같아질 때 경사로를 설치
                이후 추세를 중립으로 변경
                """
                for i in range(L):
                    point = (x - x_arr[d] * i, y - y_arr[d] * i)
                    if point in ramps:
                        # 경사로가 중복되면 실패값 리턴
                        return 0
                    ramps.add(point)
                p = 0
        elif current > prev:
            """
            현재 좌표가 직전 좌표보다 큰 경우
            """
            if current - 1 > prev:
                # 차이가 1보다 큰 경우 실패값 리턴
                return 0
            if p == 2 and cnt == 1:
                """
                추세가 감소였다가 증가로 변경되는 경우
                카운트가 1이면 감소추세에 사용할 경사로와 증가 추세에 사용할 경사로가 중복되어 실패값 리턴
                예시: 212
                """
                return 0

            if cnt >= L:
                """
                카운트가 경사로의 길이보다 크거나 같은 경우
                경사로 설치 후 추세를 중립으로 변경
                """
                for i in range(L):
                    point = (x - x_arr[d] * (i + 1), y - y_arr[d] * (i + 1))
                    if point in ramps:
                        return 0
                    ramps.add(point)
                p = 0
            else:
                """
                카운트가 경사로의 길이보다 짧은 경우 실패값 리턴
                """
                return 0
            p = 1
            cnt = 1
        elif current < prev:
            """
            현재 좌표가 직전 좌표보다 작은 경우
            """
            if current + 1 < prev:
                # 차이가 1보다 큰 경우 실패값 리턴
                return 0
            if p == 2:
                """
                감소 추세였다가 다시 감소 추세인 경우
                카운트를 확인
                예시 : 332211
                """
                if cnt >= L:
                    """
                    카운트가 경사로의 길이보다 크거나 같은 경우
                    경사로 설치 후 추세를 중립으로 변경
                    """
                    p = 0
                    for i in range(L):
                        point = (x - x_arr[d] * i, y - y_arr[d] * i)
                        if point in ramps:
                            return 0
                        ramps.add(point)
                else:
                    """
                    카운트가 경사로의 길이보다 짧은 경우 실패값 리턴
                    """
                    return 0
            p = 2
            cnt = 1

        prev = current


answer = 0

for i in range(N):
    answer += check(i, 0, 0)
    answer += check(0, i, 1)

print(answer)
