# 30022 행사 준비

N, A, B = list(map(int, input().split()))
"""
1번 상점의 가격과 2번 상점의 가격 차를 기준으로 정렬
차가 적거나 혹은 음수인 경우 1번 상점에서 구매하는 것이 더 적은 금액이 듦
1번 상점에서 구매 후 나머지 물품은 모두 2번 상점에서 구매
"""
arr = sorted(
    [list(map(int, input().split())) for _ in range(N)], key=lambda t: t[0] - t[1]
)

answer = 0

for i in range(A):
    answer += arr[i][0]

for i in range(A, N):
    answer += arr[i][1]

print(answer)
