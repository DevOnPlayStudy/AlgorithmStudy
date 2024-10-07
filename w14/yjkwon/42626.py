# 더 맵게
"""
1. 최소힙을 사용합니다.
2. 힙에서 최소 숫자를 하나 뽑습니다.
3. 이 때 숫자가 K보다 크거나 같으면 종료합니다.
4. 남은 스코빌지수가 없으면 더이상 연산을 진행할수 없으므로 -1을 리턴합니다.
5. 힙에서 최소 숫자를 하나 더 뽑습니다.
6. 두 숫자를 합쳐서 힙에 다시 넣습니다.
"""

import heapq


def solution(scoville, K):
    heapq.heapify(scoville)
    answer = 0
    while True:
        s1 = heapq.heappop(scoville)
        if s1 >= K:
            break
        elif not scoville:
            return -1
        answer += 1
        s2 = heapq.heappop(scoville)
        s3 = s1 + (s2 * 2)
        heapq.heappush(scoville, s3)
    return answer
