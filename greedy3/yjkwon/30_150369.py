# 택배 배달과 수거하기


def solution(cap, n, deliveries, pickups):
    d_stack = []
    p_stack = []
    for i in range(n):
        if deliveries[i] > 0:
            d_stack.append((i, deliveries[i]))
        if pickups[i] > 0:
            p_stack.append((i, pickups[i]))
    d_cap, p_cap = cap, cap
    answer = 0
    while d_stack or p_stack:
        i, d = d_stack.pop() if d_stack else (0, 0)
        j, p = p_stack.pop() if p_stack else (0, 0)
        if d_cap == p_cap == cap:
            answer += (max(i, j) + 1) * 2
        if d_cap > 0:
            d, d_cap = max(0, d - d_cap), max(0, d_cap - d)
            if d > 0:
                d_stack.append((i, d))
        else:
            d_stack.append((i, d))
        if p_cap > 0:
            p, p_cap = max(0, p - p_cap), max(0, p_cap - p)
            if p > 0:
                p_stack.append((j, p))
        else:
            p_stack.append((j, p))
        if (
            (len(p_stack) == 0 and d_cap == 0)
            or (len(d_stack) == 0 and p_cap == 0)
            or d_cap == p_cap == 0
        ):
            d_cap, p_cap = cap, cap
    return answer
