# 2096 내려가기

N = int(input())

row = list(map(int, input().split()))
max_a, max_b, max_c = row
min_a, min_b, min_c = row

for i in range(1, N):
    row = list(map(int, input().split()))
    new_max_a = max(max_a, max_b) + row[0]
    new_max_b = max(max_a, max_b, max_c) + row[1]
    new_max_c = max(max_b, max_c) + row[2]

    new_min_a = min(min_a, min_b) + row[0]
    new_min_b = min(min_a, min_b, min_c) + row[1]
    new_min_c = min(min_b, min_c) + row[2]

    max_a, max_b, max_c = new_max_a, new_max_b, new_max_c
    min_a, min_b, min_c = new_min_a, new_min_b, new_min_c

print(max(max_a, max_b, max_c), min(min_a, min_b, min_c))
