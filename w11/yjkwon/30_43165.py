# 타겟 넘버
"""
numbers를 순회하면서 모든 값을 +와 -로 모두 계산해서
타겟과 동일한 경우 카운트를 증가
"""


def solution(numbers, target):
    numbers_len = len(numbers)
    stack = [(0, numbers[0]), (0, numbers[0] * -1)]
    answer = 0
    while stack:
        i, v = stack.pop()
        if i + 1 == numbers_len:
            if v == target:
                answer += 1
            continue
        for d in [1, -1]:
            stack.append((i + 1, v + (numbers[i + 1] * d)))
    return answer
