# 카펫

"""
카펫의 길이가 될 수 있는 값을 모두 구한다
길이와 높이를 더하고 그 값에서 중복되는 모서리 값을 뺀 수가 갈색과 같은 값을 찾는다
"""


def solution(brown, yellow):
    total = brown + yellow
    li = list()

    for i in range(1, round(total ** (1 / 2)) + 1):
        if not total % i:
            li.append(i)

    for i in li:
        width = int(total / i)
        length = i

        if (width + length) * 2 - 4 == brown:
            return [width, length]
