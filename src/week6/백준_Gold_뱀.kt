package week6

import java.util.*

/*
    N*N 보드위에서 진행됨
    뱀은 좌측 상단에 위치함
    1.뱀이 머리를다음칸에 위치
    2. 사과가 있다면 -> 사과를 먹고 꼬리는 그대로 유지됨(몸길이 1 늘어남)
    3. 사과가 없다면 -> 꼬리를 줄이고 몸길이 유지
    종료 조건 : 뱀 자신의 몸과 부딪히거나 벽에 부딪힐 경우
    기억해야하는것 뱀의 몸이 위치한 블럭

    주어지는 조건
    1. 사과의 위치 (x,y)
    2. 뱀의 이동경로 X초 뒤에 L(왼쪽) C(오른쪽) 으로 방향 회전 -> X+1초부터 바뀜


 */

private val bw = System.`out`.bufferedWriter()
private lateinit var apple: MutableSet<Pair<Int, Int>> //탐색이 간편한게 Set 사용
private val snake = LinkedList<Pair<Int, Int>>()
private val dx = intArrayOf(-1, 0, 1, 0)
private val dy = intArrayOf(0, 1, 0, -1)

fun moveSnake(snake: LinkedList<Pair<Int, Int>>, dir: Int) {
    val snakeHead = snake.peek()
    val x = snakeHead.first + dx[dir]
    val y = snakeHead.second + dy[dir]

    snake.addFirst(x to y)

    if (apple.contains(x to y)) {
        apple.remove(x to y)
    } else {
        snake.removeLast()
    }
}

fun rotateDirection(dir: Int, turn: String): Int =
    when (turn) {
        "L" -> (dir + 3) % 4
        "D" -> (dir + 1) % 4
        else -> dir
    }


fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val appleNum = readLine().toInt()
    apple = mutableSetOf()
    repeat(appleNum) {
        apple.add(readLine().split(" ").map { it.toInt() }.let { (x, y) -> x - 1 to y - 1 }
        )
    }

    var time = 1
    var direction = 1 // 0:"UP", 1:"RIGHT", 2:"DOWN", 3:"LEFT"
    val commandCount = readLine().toInt() // 읽기 편하게 commandCount 명시해주자!
    val rotateTime = Array(commandCount) {
        readLine().split(" ").let { (x, y) -> x.toInt() to y }
    }
    var commandIdx = 0

    snake.addFirst(0 to 0)
    while (true) {
        val snakeHead = snake[0]
        val nx = snakeHead.first + dx[direction] // 움직인거니까 앞으로 nx ny로 표현하자
        val ny = snakeHead.second + dy[direction]
        val next = nx to ny
        if (snake.contains(next) ||  nx !in 0 until N || ny !in 0 until N) break
        //x >= N || y >= N || x < 0 || y < 0 -> nx !in 0 until N || ny !in 0 until N

        moveSnake(snake, direction)

        if (commandIdx != commandCount && time == rotateTime[commandIdx].first) {
            direction = rotateDirection(direction, rotateTime[commandIdx++].second)
        }
        time++
    }

    bw.append("${time}").flush()
}
