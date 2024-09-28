# 30018 타슈
N = int(input())
a_arr = list(map(int, input().split()))
b_arr = list(map(int, input().split()))

answer = 0
for i in range(N):
    a = a_arr[i]
    b = b_arr[i]
    c = a - b
    if c > 0:
        answer += c

print(answer)
