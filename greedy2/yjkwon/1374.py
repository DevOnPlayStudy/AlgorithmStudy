# 1374 강의실

N = int(input())
arr = []

for _ in range(N):
    n, start, end = list(map(int, input().split()))
    arr.append((start, 1))
    arr.append((end, -1))

arr.sort()

answer = 0
current = 0

for _, v in arr:
    current += v
    answer = max(answer, current)

print(answer)
