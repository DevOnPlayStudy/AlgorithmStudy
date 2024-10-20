# 단어 변환

"""
백트래킹을 이용합니다.
현재까지 사용하지 않은 단어 중 철자가 하나 다른 단어를 사용해 다음 스텝을 진행합니다.
현재 단어가 타겟과 같다면 정답을 갱신합니다.
정답이 발견되지 않으면 0, 발견되면 정답을 리턴합니다.
"""


def solution(begin, target, words):
    answer = len(words) + 1

    def back(visited, begin):
        nonlocal answer
        if begin == target:
            answer = min(len(visited) - 1, answer)

        for w in words:
            if w in visited:
                continue

            n = 0
            for a, b in zip(begin, w):
                if a == b:
                    n += 1
            if n == len(begin) - 1:
                visited.add(w)
                back(visited, w)
                visited.remove(w)

    back(set([begin]), begin)

    return answer if answer < len(words) + 1 else 0
