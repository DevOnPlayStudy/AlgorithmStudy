# 과제 진행하기

"""
plans에서 하나를 뽑습니다
현재 시간과 시작 시간을 비교하여
1. 더 큰 경우
stack이 있으면 stack을 진행합니다.
stack이 없으면 plans를 진행합니다.
2. 같은 경우
plans를 진행합니다.

진행하는 방법
1. plans가 없는 경우
그대로 진행합니다.
2. plans가 있는 경우
진행할 과제와 plans의 맨 첫 번째의 시간차를 비교합니다.
2-1. 차가 duration보다 긴 경우
진행할 과제를 완료합니다.
2-2. 차가 duration보다 짧은 경우
진행하고 있는 과제의 duration - 차 만큼 계산하여 stack에 집어 넣습니다.
"""


from collections import deque


def cal_time(t, a):
    h, m = list(map(int, t.split(":")))
    a = int(a)
    h += a // 60
    m += a % 60
    if m >= 60:
        h += 1
        m -= 60
    return f"{h}:{m}"


def compare_time(a, b):
    ah, am = list(map(int, a.split(":")))
    bh, bm = list(map(int, b.split(":")))
    a, b = (ah, am), (bh, bm)
    if a == b:
        return 0
    elif a > b:
        return 1
    else:
        return -1


def get_diff_time(a, b):
    ah, am = list(map(int, a.split(":")))
    bh, bm = list(map(int, b.split(":")))
    return (ah * 60 + am) - (bh * 60 + bm)


def solution(plans):
    answer = []
    stack = deque()
    plans.sort(key=lambda x: x[1])
    plans = deque(plans)
    current_time = plans[0][1]  # 현재 시간

    def do_plan(plan):
        play_time = int(plan[2])
        if plans:
            diff = get_diff_time(plans[0][1], current_time)
            if diff < play_time:
                play_time -= diff
                plan[2] = str(play_time)
                stack.append(plan)
                return cal_time(current_time, diff)
        answer.append(plan[0])
        return cal_time(current_time, plan[2])

    while plans or stack:
        if not plans:
            s = stack.pop()
            answer.append(s[0])
            continue

        p = plans.popleft()
        res = compare_time(p[1], current_time)

        if res == 1:
            if not stack:
                next_plan = p
                current_time = p[1]
            else:
                next_plan = stack.pop()
                plans.appendleft(p)
        else:
            next_plan = p

        current_time = do_plan(next_plan)

    return answer
