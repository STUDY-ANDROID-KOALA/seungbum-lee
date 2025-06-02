package week8

import java.util.*

private val bw = System.`out`.bufferedWriter()
private val board = Array(500) { IntArray(500) }
private val visited = Array(500) { BooleanArray(500) }
private val dx = intArrayOf(1, 0, -1, 0)
private val dy = intArrayOf(0, 1, 0, -1)


fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val queue = ArrayDeque<Pair<Int,Int>>()
    var max = 0
    var num = 0
    repeat(n) { i ->
        val str = StringTokenizer(readLine())
        repeat(m) { j ->
            val painted = str.nextToken().toInt()
            board[i][j] = painted
        }
    }

    for( i in 0 ..< n){
        for(j in 0 ..< m){
            if(board[i][j] == 1 && !visited[i][j]){
                num++
                var count = 0
                visited[i][j] = true
                queue.offer(i to j)
                count++

                while(!queue.isEmpty()){
                    val next = queue.poll()
                    for( dir in 0 .. 3){
                        val x = next.first + dx[dir]
                        val y = next.second + dy[dir]

                        if(x !in 0..<n || y !in 0 ..< m) continue
                        if(board[x][y] != 1 || visited[x][y]) continue

                        visited[x][y] = true
                        queue.offer(x to y)
                        count++
                    }
                }
                max = max.coerceAtLeast(count)
            }
        }
    }
    bw.append("$num\n$max").flush()
}