package week8

import java.util.*

private val bw = System.`out`.bufferedWriter()
private val board = Array(1000) { IntArray(1000) }
private val visited = Array(1000) { BooleanArray(1000) }
private val dx = intArrayOf(1, 0, -1, 0)
private val dy = intArrayOf(0, 1, 0, -1)
private var timer = -1

fun main() = with(System.`in`.bufferedReader()) {
    val (m,n) = readLine().split(" "). map { it.toInt() }
    val queue = ArrayDeque<Pair<Int, Int>>()

    repeat(n) { i ->
        val str = StringTokenizer(readLine())
        repeat(m) { j ->
            val riped = str.nextToken().toInt()
            board[i][j] = riped
            if (riped == 1) {
                visited[i][j] = true
                queue.offer(i to j)
            }
        }
    }

    while (!queue.isEmpty()) {
        val size = queue.size
        timer++
        repeat(size) {
            val next = queue.poll()
            for (dir in 0..3) {
                val nx = next.first + dx[dir]
                val ny = next.second + dy[dir]

                if (nx !in 0..<n || ny !in 0..<m) continue
                if (board[nx][ny] != 0 || visited[nx][ny]) continue

                board[nx][ny] = 1
                queue.offer(nx to ny)
            }
        }

    }

    repeat(n) { i ->
        repeat(m) { j ->
            if (board[i][j] == 0) {
                bw.append("-1").flush()
                return
            }
        }
    }

    bw.append("$timer").flush()
}
