# N으로 표현

"""
각 N의 갯수별로 나올 수 있는 경우의 수를 저장할 배열을 만듭니다.
N(x)의 경우 1부터 x - 1 까지 순회하면서 경우의 수를 모두 구합니다.
예를 들어 x = 6의 경우
    N(1) x N(5)
    N(2) x N(4)
    N(3) x N(3)
    N(4) x N(2)
    N(5) x N(1)
의 경우를 모두 저장합니다.
이 때 타겟 number가 발견되면 그 때의 x값을 리턴합니다.
"""


def solution(N, number):
    dp = [{N}, {int(f"{N}{N}"), N + N, N - N, N * N, N // N}]

    for i, l in enumerate(dp):
        if number in l:
            return i + 1

    x = 2
    answer = -1
    while x < 9:
        x += 1
        l = {int(f"{N}" * x)}

        for i in range(x - 1):
            for a in dp[i]:
                for b in dp[x - i - 2]:
                    l.add(a + b)
                    l.add(a - b)
                    l.add(a * b)
                    if b:
                        l.add(a // b)

        if number in l:
            answer = x
            break

        dp.append(l)

    return answer
