"""
1 -> 1
2 -> 1 + 1, 2 -> 2
3 -> 1 + 1 + 1, 1 + 2, 2 + 1, 3 -> 4
4 -> 1 + f(3), 2 + f(2), 3 + f(1) -> 7
5 -> 7 + 4 + 2 -> 13
6 -> 13 + 7 + 4 -> 24
"""

T = int(input())

for _ in range(T):
    memoization = [1, 2, 4, 7]
    n = int(input())

    for i in range(4, n):
        memoization.append(memoization[i - 1] + memoization[i - 2] + memoization[i - 3])

    print(memoization[n - 1])
