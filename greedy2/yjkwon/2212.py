# 2212 센서

"""
6
2
1 6 9 3 6 7

1 3 6 6 7 9
[2] [  3  ]
2 + 3 = 5

10
5
20 3 14 6 7 8 18 10 12 15

3 6 7 8 10 12 14 15 18 20
[0] [ 2 ] [ 2 ] [ 1 ] [2]
7
"""

N = int(input())
K = int(input())
arr = sorted(list(map(int, input().split())))

if K >= N:
    print(0)
else:
    points = []
    for i in range(N - 1):
        points.append((arr[i + 1] - arr[i], i + 1))
    points.sort(reverse=True)

    groups = []
    start = 0
    for _, i in sorted(points[: K - 1], key=lambda t: t[1]):
        groups.append(arr[start:i])
        start = i
    groups.append(arr[start:N])

    answer = sum(group[-1] - group[0] for group in groups)
    print(answer)
