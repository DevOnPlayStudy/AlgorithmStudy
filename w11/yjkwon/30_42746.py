# 가장 큰 수

"""
두 숫자의 첫 번째 자리가 같은 경우
두 수를 문자열 형태에서 더해서 큰 값으로 정렬
"""

from functools import cmp_to_key


def compare(x, y):
    if x[0] == y[0]:
        return -1 if x + y < y + x else 1
    return -1 if x < y else 1


def solution(numbers):
    numbers = list(sorted(map(str, numbers), key=cmp_to_key(compare), reverse=True))
    answer = "".join(numbers)
    return answer if answer.replace("0", "") else "0"
