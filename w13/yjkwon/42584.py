# 주식 가격

"""
1. 주식 가격 배열과 동일한 정답 배열을 만듭니다.
2. 주식 가격 배열을 순회하면서 스택에 담습니다.
3. 스택이 존재하고, 현재 주식의 가격이 스택의 마지막 주식 가격보다 낮은경우 스택에서 하나씩 제거하면서 정답을 찾습니다.
4. 주식 배열을 모두 순쇠하였을 때 스택이 남는경우 마지막 까지 가격이 떨어지지 않은 것으로 간주하고 정답에 기록합니다.
"""


def solution(prices):
    s = []
    answer = [0 for _ in prices]
    for i, p in enumerate(prices):
        while s and s[-1][-1] > p:
            j, c = s.pop()
            answer[j] = i - j
        s.append((i, p))
    total = len(prices)
    while s:
        i, p = s.pop()
        answer[i] = total - i - 1
    return answer
