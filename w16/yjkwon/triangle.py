# Triangle

"""
삼각형의 최상단을 배열에 저장합니다.
두 번째 열 부터 순회합니다.
열의 숫자들과 이전 열의 숫자 조합 중 가장 작은 수를 배열에 추가합니다.
저장된 가장 마지막 열의 숫자들 중 가장 작은 숫자를 리턴합니다.
"""

from typing import List


class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        dp = [triangle[0]]

        for i, row in enumerate(triangle[1:]):
            tmp = []
            for j, v in enumerate(row):
                if j == 0:
                    tmp.append(dp[i][0] + v)
                elif j == len(row) - 1:
                    tmp.append(dp[i][-1] + v)
                else:
                    tmp.append(min(dp[i][j - 1] + v, dp[i][j] + v))
            dp.append(tmp)

        return min(dp[-1])
