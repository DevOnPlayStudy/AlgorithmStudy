# 숫자 블록

"""
범위에 있는 수 들을 순회합니다.
수의 최대 약수를 구합니다.
이 때 1천만을 넘는 수는 제외합니다.
"""


def solution(begin, end):
    answer = []
    for i in range(begin, end + 1):
        if i == 1:
            answer.append(0)
        else:
            measures = []
            for j in range(2, min(int(i**0.5) + 1, 10000000)):
                if not i % j:
                    if j in measures:
                        break
                    measures.append(j)
                    v = i // j
                    if v <= 10000000:
                        measures.append(v)
                        break
            if measures:
                answer.append(max(measures))
            else:
                answer.append(1)

    return answer
