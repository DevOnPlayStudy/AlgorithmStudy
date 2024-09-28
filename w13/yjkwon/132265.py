# 롤케이크 자르기

"""
1. 토핑의 가짓수를 저장할 배열을 두 개 만듭니다.
2. 하나는 앞에서부터 저장하고, 다른 하나는 뒤에서부터 저장합니다.
3. 배열을 순회하면서 토핑의 가짓수를 두 배열에 저장합니다.
4. 뒤에서 부터 저장한 배열을 뒤집습니다.
5. index를 0부터 배열의 길이 -1 까지 순회하면서 가짓수를 i와 i+1 로 비교하면서 가짓수가 같은 갯수를 셉니다.
"""


def solution(topping):
    l1 = []
    l2 = []
    s1 = set()
    s2 = set()
    for i in range(len(topping)):
        s1.add(topping[i])
        s2.add(topping[-(i + 1)])
        l1.append(len(s1))
        l2.append(len(s2))
    l2 = l2[::-1]
    answer = 0
    for i in range(len(topping) - 1):
        if l1[i] == l2[i + 1]:
            answer += 1
    return answer
