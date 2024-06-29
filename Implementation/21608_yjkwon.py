N = int(input())
arr = [list(map(int, input().split())) for _ in range(N**2)]


class Student:
    num: int
    preferred_students: list[int]

    def __init__(self, num: int, preferred_students: list[int]):
        self.num = num
        self.preferred_students = preferred_students


class Point:
    x: int
    y: int
    near: list["Point"]
    student: Student | None = None
    empty_spaces: int

    def __init__(self, x: int, y: int, empty_spaces: int):
        self.x = x
        self.y = y
        self.empty_spaces = empty_spaces
        self.near = []

    @property
    def near_students(self):
        return [point.student.num for point in self.near if point.student is not None]

    @property
    def satisfaction(self):
        intersection = list(
            set(self.student.preferred_students) & set(self.near_students)
        )
        match len(intersection):
            case 1:
                return 1
            case 2:
                return 10
            case 3:
                return 100
            case 4:
                return 1000
            case _:
                return 0


points: list[Point] = []


def sort_points(student: Student):
    points.sort(
        key=lambda point: (
            point.student is not None,
            -len(set(point.near_students) & set(student.preferred_students)),
            -point.empty_spaces,
            point.x,
            point.y,
        )
    )


def search_point(x: int, y: int):
    for point in points:
        if point.x == x and point.y == y:
            return point


def init_points():
    for i in range(N):
        for j in range(N):
            empty_spaces = 4
            if i == 0 or i == N - 1:
                empty_spaces -= 1
            if j == 0 or j == N - 1:
                empty_spaces -= 1
            point = Point(i, j, empty_spaces)
            if i != 0:
                left_point = search_point(i - 1, j)
                left_point.near.append(point)
                point.near.append(left_point)
            if j != 0:
                top_point = search_point(i, j - 1)
                top_point.near.append(point)
                point.near.append(top_point)
            points.append(point)


def set_students():
    for row in arr:
        num = row[0]
        preferred_students = row[1:]
        student = Student(num, preferred_students)
        sort_points(student)
        point = points[0]
        point.student = student
        for p in point.near:
            p.empty_spaces -= 1


init_points()
set_students()

res = sum([point.satisfaction for point in points])

print(res)
