# 2133 타일 채우기

"""
f(0) = 1
f(1) = 0
f(2) = 3
f(3) = 0
f(4) = 11 = f(2) * 3 + f(0) * 2
    - 2/2 = 3 * 3 = 9
    - 4 = 2
f(5) = 0
f(6) = 41 = f(4) * 3 + (f(2) + f(0)) * 2
    - 2/2/2 = 3 * 3 * 3 = 27
    - 2/4 = 3 * 2
    - 4/2 = 2 * 3
    - 6 = 2
f(7) = 0
f(8) = 149 = f(6) * 3 + (f(4) + f(2) + f(0)) * 2
    - 2/2/2/2 = 3 * 3 * 3 * 3 = 81
    - 2/2/4 = 3 * 3 * 2 = 18
    - 2/4/2 = 3 * 2 * 3 = 18
    - 4/2/2 = 2 * 3 * 3 = 18
    - 2/6 = 3 * 2 = 6
    - 6/2 = 2 * 3 = 6
    - 8 = 2
"""

N = int(input())
memoization = [1]

for i in range(1, N + 1):
    if i % 2 == 1:
        memoization.append(0)
        continue
    num = memoization[i - 2] * 3
    num2 = 0
    if i > 3:
        for j in range(0, i - 3, 2):
            num2 += memoization[j]
    num += num2 * 2
    memoization.append(num)

print(memoization[N])
