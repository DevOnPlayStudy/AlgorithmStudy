import sys

input = sys.stdin.readline

N = int(input())
arr = sorted(
    [list(map(int, input().split())) for _ in range(N)], key=lambda x: (x[1], x[0])
)  # 종료시간 순서로 정렬

if arr[0][0] > arr[0][1]:
    # 첫 번째 일의 기한이 수행시간보다 빠른 경우
    print(-1)
else:
    answer = arr[0][1] - arr[0][0]  # 첫 번째 일을 가장 늦게 시작 할 수 있는 시간
    time = arr[0][1]  # 일이 종료된 후의 시간

    for task in arr[1:]:
        end = time + task[0]  # 현재 일이 종료된 후의 시간
        gap = end - task[1]  # 일의 기한과의 갭
        if gap > 0:
            # 갭이 존재하는 경우
            if gap > answer:
                # 갭이 여유시간보다 큰 경우
                answer = -1
                break
            answer -= gap  # 여유 시간에서 갭을 제함
            time = end - gap  # 종료 된 후의 시간을 갱신
        else:
            time = end  # 종료된 후의 시간을 갱신

    print(answer)
