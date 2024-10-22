# 뒤에 있는 큰 수 찾기
"""
1. 배열을 순회합니다.
2. 현재 숫자가 스텍에 있는 마지막 숫자보다 크면 정답에 기록합니다.
"""


def solution(numbers):
    stack = []
    answer = [-1] * len(numbers)
    for i, n in enumerate(numbers):
        while stack and stack[-1][1] < n:
            j, _ = stack.pop()
            answer[j] = n
        stack.append((i, n))

    return answer
