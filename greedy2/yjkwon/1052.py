# 1052 물병

N, K = list(map(int, input().split()))

answer = -1
for i in range(N):
    n = N + i
    if bin(n).count("1") <= K:
        answer = i
        break
print(answer)
