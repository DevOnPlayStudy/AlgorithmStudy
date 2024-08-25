# 1931 회의실 배정

N = int(input())
# 빨리 끝나는 순서대로 정렬, 끝나는 시간이 같으면 시작시간이 더 빠른 순서로 정렬
arr = sorted(
    [list(map(int, input().split())) for _ in range(N)], key=lambda l: (l[1], l[0])
)

first = arr.pop(0)
end = first[1]
answer = 1

for m in arr:
    m_start, m_end = m
    if m_start >= end:
        # 시작시간이 이전 회의의 끝나는 시간보다 같거나 늦으면 선택
        answer += 1
        end = m_end

print(answer)
