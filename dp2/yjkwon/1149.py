# 1149 RGB 거리

N = int(input())
r, g, b = list(map(int, input().split()))

for i in range(1, N):
    cr, cg, cb = list(map(int, input().split()))
    new_r = min(g, b) + cr
    new_g = min(r, b) + cg
    new_b = min(r, g) + cb
    r, g, b = new_r, new_g, new_b

print(min(r, g, b))
