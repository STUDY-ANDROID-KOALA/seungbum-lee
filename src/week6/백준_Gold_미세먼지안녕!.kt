package week6

import java.util.*

private val dx = intArrayOf(-1, 0, 1, 0) // 상우하좌
private val dy = intArrayOf(0, 1, 0, -1)

fun main() = with(System.`in`.bufferedReader()) {
    val (r, c, t) = readLine().split(" ").map { it.toInt() }
    val board = Array(r) { IntArray(c) }
    val airCleaners = mutableListOf<Pair<Int, Int>>() // 공기청정기 위치 저장

    repeat(r) { i ->
        val st = StringTokenizer(readLine())
        repeat(c) { j ->
            board[i][j] = st.nextToken().toInt()
            if (board[i][j] == -1) airCleaners.add(i to j)
        }
    }

    repeat(t) {
        diffuse(board, r, c, airCleaners)
        circulate(board, r, c, airCleaners[0].first, airCleaners[1].first)
    }

    val result = board.sumOf { row -> row.sum() } + 2 // 공기청정기 -1이 두 개 있으므로 +2
    println(result)
}

fun diffuse(board: Array<IntArray>, r: Int, c: Int, airCleaners: List<Pair<Int, Int>>) {
    val temp = Array(r) { IntArray(c) }
    for (i in 0 until r) {
        for (j in 0 until c) {
            if (board[i][j] > 0) {
                val spreadAmount = board[i][j] / 5
                var count = 0
                for (d in 0..3) {
                    val nx = i + dx[d]
                    val ny = j + dy[d]
                    if (nx in 0 until r && ny in 0 until c && board[nx][ny] != -1) {
                        temp[nx][ny] += spreadAmount
                        count++
                    }
                }
                board[i][j] -= spreadAmount * count
            }
        }
    }

    for (i in 0 until r) {
        for (j in 0 until c) {
            board[i][j] += temp[i][j]
        }
    }
}

fun circulate(board: Array<IntArray>, r: Int, c: Int, up: Int, down: Int) {
    for (i in up - 1 downTo 1) board[i][0] = board[i - 1][0]
    for (j in 0 until c - 1) board[0][j] = board[0][j + 1]
    for (i in 0 until up) board[i][c - 1] = board[i + 1][c - 1]
    for (j in c - 1 downTo 2) board[up][j] = board[up][j - 1]
    board[up][1] = 0

    for (i in down + 1 until r - 1) board[i][0] = board[i + 1][0]
    for (j in 0 until c - 1) board[r - 1][j] = board[r - 1][j + 1]
    for (i in r - 1 downTo down + 1) board[i][c - 1] = board[i - 1][c - 1]
    for (j in c - 1 downTo 2) board[down][j] = board[down][j - 1]
    board[down][1] = 0
}

/*
    각 미세먼지는 1초만다 확산한다 -> 미세먼지 칸/5의 크기만큼
        단, 공기청정기 쪽으로는 확산하지 않는다.
    확산후 공기 청정기가 위쪽은 반시계방향 , 아래쪽은 시계 방향으로 확산한다.
    구해야하는것: T 초후 남아있는 미세먼지의 개수
    visited로 구분해야겠지. visited인곳을 모두 방문하면서 5보다 작다면 , 기준을 넘는다면 모두 pass

    공기청정기는 한칸씩 밀되 이때 벽은 만난다면 위로 밀어주는게 포인트
    위쪽 -> 반시계
    아래쪽 -> 시계
    이때 공기청정기에 도달하면 없앤다.
 */
//
//private val bw = System.`out`.bufferedWriter()
//private val dx = intArrayOf(0, 1, 0, -1)
//private val dy = intArrayOf(1, 0, -1, 0)
//
//fun main() = with(System.`in`.bufferedReader()) {
//    val (r, c, time) = readLine().split(" ").map { it.toInt() }
//    var board = Array(r) { IntArray(c) }
//    val visited = Array(r) { BooleanArray(c) }
//    val queue = ArrayDeque<Pair<Int, Int>>()
//    var cleaner = 0 to 0
//    var dust = 0
//
//    repeat(r) { i ->
//        val st = StringTokenizer(readLine())
//        repeat(c) { j ->
//            board[i][j] = st.nextToken().toInt()
//            if (board[i][j] > 0) {
//                visited[i][j] = true
//                queue.add(i to j)
//                dust += board[i][j]
//            } else if (board[i][j] == -1) cleaner = i to j
//        }
//    }
//    val upperCleaner = cleaner.first - 1 to cleaner.second
//    val downCleaner = cleaner.first to cleaner.second
//
//    repeat(time) { t->
//        val size = queue.size
//        val subBoard = Array(r) { IntArray(c) { 0 } }
//        for (i in 0..<size) {
//            val next = queue.removeFirst()
//            val centerDust = board[next.first][next.second]
//            if (centerDust < 5) continue
//            val distributedDust = centerDust / 5
//            for (dir in 0..3) {
//                val x = next.first + dx[dir]
//                val y = next.second + dy[dir]
//                if (x < 0 || x >= r || y < 0 || y >= c) continue
//                if ((x == upperCleaner.first && y == upperCleaner.second) || (x == downCleaner.first && y == downCleaner.second)) continue
//                if (board[x][y] == 0) queue.add(x to y)
//                subBoard[x][y] += distributedDust
//                board[next.first][next.second] -= distributedDust
//            }
//        }
//
//        for (i in 0..<r) {
//            for (j in 0..<c) {
//                board[i][j] += subBoard[i][j]
//            }
//        }
//
//        for (i in upperCleaner.first - 1 downTo 1) {
//            if (i == upperCleaner.first - 1) {
//                dust -= board[i][0]
//            }
//            board[i][0] = board[i - 1][0]
//        }
//        for (i in downCleaner.first + 1..<r - 1) {
//            if (i == downCleaner.first + 1) {
//                dust -= board[i][0]
//            }
//            board[i][0] = board[i + 1][0]
//        }
//
//        for (i in 0..<c - 1) {
//            board[0][i] = board[0][i + 1]
//            board[r - 1][i] = board[r - 1][i + 1]
//        }
//
//        for (i in 1..<upperCleaner.first) {
//            board[i][c - 1] = board[i - 1][c - 1]
//        }
//        for (i in r - 2 downTo downCleaner.first + 1) {
//            board[i][c - 1] = board[i + 1][c - 1]
//        }
//        for (i in c - 1 downTo 2) {
//            board[upperCleaner.first][i] = board[upperCleaner.first][i - 1]
//            board[downCleaner.first][i] = board[downCleaner.first][i - 1]
//        }
//    }
//    print(dust)
//}



