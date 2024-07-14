# 배열 돌리기 3
N, M, R = list(map(int, input().split()))
arr = [list(input().split()) for _ in range(N)]
commands = list(map(int, input().split()))


def parr():
    for row in arr:
        print(" ".join(row))


def fn1():
    # 배열을 상하 반전시키는 연산
    return list(reversed(arr))


def fn2():
    # 배열을 좌우 반전시키는 연산
    return [list(reversed(row)) for row in arr]


def fn3():
    # 오른쪽으로 90도 회전시키는 연산
    tmp = []
    for j in range(M):
        row = []
        for i in range(N - 1, -1, -1):
            row.append(arr[i][j])
        tmp.append(row)
    return tmp


def fn4():
    # 왼쪽으로 90도 회전시키는 연산
    tmp = []
    for j in range(M - 1, -1, -1):
        row = []
        for i in range(N):
            row.append(arr[i][j])
        tmp.append(row)
    return tmp


def fn5():
    # 1번 그룹의 부분 배열을 2번 그룹 위치로, 2번을 3번으로, 3번을 4번으로, 4번을 1번으로 이동시키는 연산
    tmp = []
    for i in range(N // 2, N):
        row = []
        for j in range(M // 2):
            row.append(arr[i][j])
        tmp.append(row)

    for i in range(N // 2):
        for j in range(M // 2):
            tmp[i].append(arr[i][j])

    for i in range(N // 2, N):
        row = []
        for j in range(M // 2, M):
            row.append(arr[i][j])
        tmp.append(row)

    for i in range(N // 2):
        for j in range(M // 2, M):
            tmp[N // 2 + i].append(arr[i][j])

    return tmp


def fn6():
    # 1번 그룹의 부분 배열을 4번 그룹 위치로, 4번을 3번으로, 3번을 2번으로, 2번을 1번으로 이동시키는 연산
    tmp = []
    for i in range(N // 2):
        row = []
        for j in range(M // 2, M):
            row.append(arr[i][j])
        tmp.append(row)

    for i in range(N // 2, N):
        for j in range(M // 2, M):
            tmp[i - N // 2].append(arr[i][j])

    for i in range(N // 2):
        row = []
        for j in range(M // 2):
            row.append(arr[i][j])
        tmp.append(row)

    for i in range(N // 2, N):
        for j in range(M // 2):
            tmp[i].append(arr[i][j])

    return tmp


for c in commands:
    N, M = len(arr), len(arr[0])
    match c:
        case 1:
            arr = fn1()
        case 2:
            arr = fn2()
        case 3:
            arr = fn3()
        case 4:
            arr = fn4()
        case 5:
            arr = fn5()
        case 6:
            arr = fn6()

parr()
