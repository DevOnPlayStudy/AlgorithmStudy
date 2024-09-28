# 광물 캐기

"""
1. 주어진 광물들을 5개씩 분리합니다
2. 광물들을 가치가 높은 순서로 정렬합니다
3. 분리된 광물들을 가치가 높은 곡괭이 순서로 캡니다
"""


def custom_key(ms):
    d = ms.count("diamond")
    i = ms.count("iron")
    s = ms.count("stone")
    return d * 25 + i * 5 + s


def solution(picks, minerals):
    sliced = []
    for i in range(0, min(len(minerals), sum(picks) * 5), 5):
        ms = minerals[i : i + 5]
        sliced.append(ms)
    sliced.sort(key=custom_key, reverse=True)

    answer = 0

    def mine(ms):
        count = 0
        p = None
        for i in range(3):
            if picks[i] > 0:
                picks[i] -= 1
                p = i
                break
        for m in ms:
            if p == 0:
                count += 1
            elif p == 1:
                if m == "diamond":
                    count += 5
                else:
                    count += 1
            else:
                if m == "diamond":
                    count += 25
                elif m == "iron":
                    count += 5
                else:
                    count += 1
        return count

    for ms in sliced:
        answer += mine(ms)

    return answer
