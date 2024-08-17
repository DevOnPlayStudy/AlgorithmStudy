# 18353 병사 배치하기

N = int(input())
arr = list(map(int, input().split()))
arr = list(reversed(arr))

memoization = [1 for _ in range(N)]

for i in range(1, N):
    for j in range(i):
        if arr[j] < arr[i]:
            memoization[i] = max(memoization[i], memoization[j] + 1)

print(N - max(memoization))
