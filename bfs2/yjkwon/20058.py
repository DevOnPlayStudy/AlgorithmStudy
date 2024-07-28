# 20058 마법사 상어와 파이어스톰
N, Q = list(map(int, input().split()))
L = 2**N
board = [list(map(int, input().split())) for _ in range(L)]
commands = list(map(int, input().split()))

d_arr = [(0, -1), (0, 1), (-1, 0), (1, 0)]


def print_board() -> None:
    for row in board:
        print(row)


def copy_board() -> list[list[int]]:
    return [list(row) for row in board]


def spin_board(x, y, k) -> None:
    copied_board = copy_board()
    for r in range(k):
        for c in range(k):
            board[x + c][y + k - 1 - r] = copied_board[x + r][y + c]


def iter_board(k: int) -> None:
    for i in range(L // k):
        for j in range(L // k):
            spin_board(i * k, j * k, k)


def check_decrease(x: int, y: int) -> bool:
    count = 0
    for dx, dy in d_arr:
        xp, yp = x + dx, y + dy
        if xp < 0 or xp > L - 1 or yp < 0 or yp > L - 1:
            continue
        count += int(bool(board[xp][yp]))

    return count < 3


def decrease() -> None:
    targets = []
    for i in range(L):
        for j in range(L):
            if check_decrease(i, j):
                targets.append((i, j))

    for i, j in targets:
        if board[i][j] > 0:
            board[i][j] -= 1


def get_count() -> int:
    count = 0

    for row in board:
        count += sum(row)

    return count


def get_block() -> int:
    visited = set()
    answers = []

    for i in range(L):
        for j in range(L):
            if not board[i][j] or (i, j) in visited:
                continue
            visited.add((i, j))

            queue = [(i, j)]
            _visited = set(queue)
            answer = 0

            while queue:
                x, y = queue.pop(0)
                answer += 1

                for dx, dy in d_arr:
                    xp, yp = x + dx, y + dy
                    if (
                        xp < 0
                        or xp > L - 1
                        or yp < 0
                        or yp > L - 1
                        or (xp, yp) in _visited
                        or not board[xp][yp]
                    ):
                        continue

                    _visited.add((xp, yp))
                    visited.add((xp, yp))
                    queue.append((xp, yp))

            answers.append(answer)

    return max(answers) if answers else 0


for command in commands:
    iter_board(2**command)
    decrease()


count = get_count()
block = get_block()
print(count)
print(block)
