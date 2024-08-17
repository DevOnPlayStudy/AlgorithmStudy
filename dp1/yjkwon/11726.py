"""
1. n = 1 -> 1
2. n = 2 -> 2
3. n = 3 -> 3
4. n = 4 -> 5
"""

N = int(input())

memoization = [1, 2]

for i in range(2, N):
    memoization.append((memoization[i - 1] + memoization[i - 2]) % 10007)

print(memoization[N - 1])
