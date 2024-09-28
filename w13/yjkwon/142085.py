# 디펜스 게임

"""
1. 적들을 순회하면서 라운드 수를 셉니다.
2. 라운드를 진행하면서 처치한 적들의 수를 기록할 최대힙을 만듭니다.
3. 남은 병사의 수가 라운드를 해결하는데 적합한 경우, 남은 병사의 수에서 적의 수를 제 하고, 힙에 저장합니다.
4. 적의 수가 남은 병사의 수보다 많고, 무적권이 남아있는경우
4-1. 힙이 존재하는 경우, 즉 진행한 이전 라운드가 존재하는 경우, 힙에서 최대 값을 가져옵니다.
4-2. 이 때 이전 라운드 중 최대 값이 현재 라운드 보다 큰 경우, 이전 라운드를 무적권으로 막은것으로 보고, 무적권을 사용한 후
    이전 라운드 적의 수 만큼 병사수를 회복합니다. 즉, 이전 라운드를 실시하지 않은 것으로 봅니다.
4-3. 현재 라운드의 값이 큰 경우, 현재 라운드를 무적권으로 막습니다.
5. 무적권이 없는 경우 순회를 종료합니다.
"""

import heapq


def solution(n, k, enemy):
    heap = []
    answer = 0
    for e in enemy:
        answer += 1
        if n >= e:
            n -= e
            heapq.heappush(heap, -e)
        elif k:
            if heap:
                t = -(heapq.heappop(heap))
                if t > e:
                    n += t
                    n -= e
                    heapq.heappush(heap, -e)
                else:
                    heapq.heappush(heap, -t)
            k -= 1
        else:
            answer -= 1
            break
    return answer
