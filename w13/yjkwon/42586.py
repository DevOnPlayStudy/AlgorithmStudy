# 기능 개발

"""
1. 작업에 속도를 더해가면서 작업이 완료되었는지 확인합니다.
2. 만약 첫 번째 작업이 완료 되고, 직전 작업이 완료되었으면 삭제 대상에 담습니다.
3. 작업을 삭제하고 삭제된 작업의 수를 셉니다.
"""


def solution(progresses, speeds):
    answer = []

    while progresses:
        for_del = []
        f = False
        p = False
        for i in range(len(progresses)):
            progresses[i] += speeds[i]
            if progresses[i] >= 100:
                if i == 0:
                    f = True
                    p = True
                    for_del.append(i)
                if i != 0 and f and p:
                    for_del.append(i)
                    p = True
            else:
                p = False

        if for_del:
            progresses = [v for i, v in enumerate(progresses) if i not in for_del]
            speeds = [v for i, v in enumerate(speeds) if i not in for_del]
            answer.append(len(for_del))
        f = False

    return answer
