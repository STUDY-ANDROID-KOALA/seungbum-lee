package week3

import java.util.*
import kotlin.math.min

private val bw = System.`out`.bufferedWriter()
private val dx = intArrayOf(1, 0, -1, 0)
private val dy = intArrayOf(0, 1, 0, -1)
private var n = 0
private var m = 0
private lateinit var board1: Array<IntArray>
private lateinit var board2: Array<IntArray>
private val cctv = LinkedList<Pair<Int, Int>>()

fun OOB(a: Int, b: Int): Boolean {
    return a < 0 || a >= n || b < 0 || b >= m
}

fun upd(a: Int, b: Int, dir: Int) {
    val direction = dir % 4
    var x = a
    var y = b
    while (true) {
        x += dx[direction]
        y += dy[direction]
        if (OOB(x, y) || board2[x][y] == 6) return
        if (board2[x][y] != 0) continue
        board2[x][y] = 7
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val nm = readLine().split(" ").map { it.toInt() }
    var min = 0
    var maxCase = 1
    n = nm[0]
    m = nm[1]
    board1 = Array(n) { IntArray(m) }
    board2 = Array(n) { IntArray(m) }


    repeat(n) { i ->
        val st = StringTokenizer(readLine())
        repeat(m) { j ->
            board1[i][j] = st.nextToken().toInt()
            if (board1[i][j] != 0 && board1[i][j] != 6) cctv.add(Pair(i, j))
            if (board1[i][j] == 0) min++
        }
    }

    repeat(cctv.size) {
        maxCase *= 4
    }

    for (tmp in 0 until maxCase) {
        board2 = Array(n) { i -> board1[i].clone() }
        var brute = tmp
        for (i in 0 until cctv.size) {
            val dir = brute % 4
            brute /= 4
            val x = cctv[i].first
            val y = cctv[i].second
            if (board1[x][y] == 1) {
                upd(x, y, dir)
            } else if (board1[x][y] == 2) {
                upd(x, y, dir)
                upd(x, y, dir + 2)
            } else if (board1[x][y] == 3) {
                upd(x, y, dir)
                upd(x, y, dir + 1)
            } else if (board1[x][y] == 4) {
                upd(x, y, dir)
                upd(x, y, dir + 1)
                upd(x, y, dir + 2)
            } else {
                upd(x, y, dir)
                upd(x, y, dir + 1)
                upd(x, y, dir + 2)
                upd(x, y, dir + 3)
            }
        }
        var value = 0
        repeat(n) { i ->
            repeat(m) { j ->
                if (board2[i][j] == 0) value++
            }
        }
        min = min(min, value)
    }
    bw.write("$min")
    bw.flush()
    bw.close()
}