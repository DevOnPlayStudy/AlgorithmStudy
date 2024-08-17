# 11399 ATM

N = int(input())
arr = list(map(int, input().split()))
arr.sort()

answers = []
wait = 0

for n in arr:
    wait += n
    answers.append(wait)

print(sum(answers))
