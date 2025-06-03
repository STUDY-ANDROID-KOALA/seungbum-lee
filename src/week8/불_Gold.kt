package week8

//해당 시간에 움직인 지훈이가 불에 안잡히면 됌. 잡힌 지훈이는 queue에서 빼버려
//불은 그냥 움직이면 되고 / 지훈이는 해당 시간에 움직인 지훈이가 모두 잡히지만 않으면됨

import java.util.*

private val bw = System.`out`.bufferedWriter()
private val board = Array(1000) { IntArray(1000) }
private val visited = Array(1000) { BooleanArray(1000) }
private val dx = intArrayOf(1, 0, -1, 0)
private val dy = intArrayOf(0, 1, 0, -1)
private var timer = 0

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val jQueue = ArrayDeque<Pair<Int, Int>>()
    val fireQueue = ArrayDeque<Pair<Int, Int>>()

    // 벽(#) : -1 , 빈공간(.) : 0 지훈이의 위치(J) : 1 , 불이난공간(F) : 2
    repeat(n) { i ->
        val str = readLine()
        repeat(m) { j ->
            val st = str[j].toString()
            board[i][j] = converter(st)
            if (board[i][j] == 1) jQueue.offer(i to j)
            if (board[i][j] == 2) {
                visited[i][j] = true
                fireQueue.offer(i to j)
            }
        }
    }

    while (!jQueue.isEmpty()) {
        timer++
        val jSize = jQueue.size
        repeat(jSize) {

            val next = jQueue.poll()
            if (board[next.first][next.second] != 2) {
                for (dir in 0..3) {
                    val nx = next.first + dx[dir]
                    val ny = next.second + dy[dir]
                    if (nx !in 0..<n || ny !in 0..<m) {
                        bw.append("$timer").flush()
                        return
                    }
                    if (board[nx][ny] != 0) continue

                    jQueue.offer(nx to ny)
                    board[nx][ny] = 1
                }
            }
        }
        val fSize = fireQueue.size
        repeat(fSize) {
            val nextFire = fireQueue.poll()
            for (dir in 0..3) {
                val nx = nextFire.first + dx[dir]
                val ny = nextFire.second + dy[dir]
                if (nx !in 0..<n || ny !in 0..<m) continue
                if (board[nx][ny] == -1 || visited[nx][ny]) continue

                fireQueue.offer(nx to ny)
                visited[nx][ny] = true
                board[nx][ny] = 2
            }
        }
    }
    bw.append("IMPOSSIBLE").flush()
}

private fun converter(st: String): Int =
    when (st) {
        "#" -> -1
        "." -> 0
        "J" -> 1
        else -> 2
    }
