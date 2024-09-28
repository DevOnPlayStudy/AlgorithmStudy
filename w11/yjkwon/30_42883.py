# 큰 수 만들기
"""
숫자들을 순회하면서 stack에 쌓는다
stack의 마지막 숫자부터 현재 숫자보다 작은 숫자면 뺀다
모든 숫자를 순회했을 때 k가 남아있다면 stack의 뒤에서부터 k 만큼 뺀다
"""


def solution(number, k):
    stack = []
    for num in number:
        while k and stack and int(stack[-1]) < int(num):
            stack.pop()
            k -= 1
        stack.append(num)

    if k:
        stack = stack[:-k]

    return "".join(stack)
