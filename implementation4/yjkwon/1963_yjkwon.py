# 소수 경로
import math

T = int(input())


def is_prime_number(x):
    """
    소수 판별 함수
    """
    for i in range(2, int(math.sqrt(x)) + 1):
        if not x % i:
            return False
    return True


def solution():
    n, np = list(map(int, input().split()))

    count = None
    success = False
    queue = [(0, n)]  # 변경 횟수, 현재 수
    visited = {n}

    while queue:
        c, x = queue.pop(0)
        if x == np:
            success = True
            count = c
            break

        x = str(x)
        for i in range(4):
            y = x[i]
            for j in range(0, 11):
                if i == 0 and j == 0:
                    # 첫 번째 자리에서 0 제외
                    continue
                elif i == 3 and j in [0, 2, 4, 6, 8]:
                    # 짝수인 수 제외
                    continue

                # 동일한 수 제외
                j = str(j)
                if j == y:
                    continue

                # 수 변환
                xp = list(x)
                xp[i] = j
                xp = int("".join(xp))

                # 소수인 경우 queue에 추가
                if xp not in visited and is_prime_number(xp):
                    queue.append((c + 1, xp))
                    visited.add(xp)

    if success:
        print(count)
    else:
        print("Impossible")


for _ in range(T):
    solution()
