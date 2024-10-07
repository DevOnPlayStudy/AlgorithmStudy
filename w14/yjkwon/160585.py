# 혼자서 하는 틱택토
"""
조건
1. O가 이겼는데 X가 두는경우
    1-1. O가 이겼는데, X의 수가 O와 같거나 큰경우
2. X가 이겼는데 O가 두는경우
    2-1. X가 이겼는데, O의 수가 X보다 하나 더 많은 경우
3. X가 먼저 두는경우
4. X가 더 많이 두는경우
5. O가 X보다 2개 더 많은경우
"""


def solution(board):
    def get_winner():
        """
        O가 이긴경우 : 1
        X가 이긴경우 : 2
        이긴쪽이 없는경우 : 0
        둘 다 이긴경우 : -1
        """
        winners = []
        rows = [0] * 3
        cols = [0] * 3
        cross = [board[0][0], board[0][2]]
        for i in range(3):
            rows[i] = board[i][0]
            for v in board[i][1:]:
                if rows[i] != v:
                    rows[i] = 0
            cols[i] = board[0][i]
            for j in range(1, 3):
                if cols[i] != board[j][i]:
                    cols[i] = 0
        for i in range(1, 3):
            if cross[0] != board[i][i]:
                cross[0] = 0
        for i, j in [(1, 1), (2, 0)]:
            if cross[1] != board[i][j]:
                cross[1] = 0

        for i in range(3):
            r, c = rows[i], cols[i]
            if r != 0 and r != ".":
                winners.append(r)
            if c != 0 and c != ".":
                winners.append(c)

        for c in cross:
            if c != 0 and c != ".":
                winners.append(c)

        winners = list(set(winners))

        if not winners:
            return 0
        elif len(winners) > 1:
            return -1
        else:
            return 1 if winners[0] == "O" else 2

    winner = get_winner()
    if winner == -1:
        return 0

    def get_count():
        o, x = 0, 0
        for i in range(3):
            for j in range(3):
                if board[i][j] == "O":
                    o += 1
                elif board[i][j] == "X":
                    x += 1
        return o, x

    o, x = get_count()
    if x > o:
        return 0
    if o > x + 1:
        return 0
    if winner == 1:
        if x >= o:
            return 0
        return 1
    elif winner == 2:
        if o > x:
            return 0
        return 1

    return 1
