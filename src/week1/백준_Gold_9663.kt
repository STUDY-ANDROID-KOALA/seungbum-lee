package week1

private val bw = System.`out`.bufferedWriter()
private var n = 0
private var count = 0
private lateinit var visited: Array<Pair<Int, Int>>

fun main() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()

    visited = Array(n + 1) { 0 to 0 }
    backTracking(1)
    bw.write("$count")
    bw.flush()
    bw.close()
}

fun backTracking(k: Int) {
    if (k == n + 1) {
        count++
        return
    }
    for (i in 1..n) {
        val location = k to i
        if (!visited.contains(location) && checkVisited(location)) {
            visited[k] = (location)
            backTracking(k + 1)
            visited[k] = 0 to 0
        }
    }
}

fun checkVisited(location: Pair<Int, Int>): Boolean {
    val x = location.first
    val y = location.second
    for (i in visited.indices) {
        val dx = visited[i].first
        val dy = visited[i].second
        if (dx == 0 && dy == 0) continue
        if (x == dx) return false
        if (y == dy) return false
        if (x + y == dx + dy) return false
        if (x - y == dx - dy) return false
    }
    return true
}

/*
    오른쪽 대각선은 x-y가 같으면 갈은곳이다
    왼쪽대각선은 x+y가 같으면 같은곳에 위치한다.
    방법 1. 모두 다 표시
     -> 백트레킹이기때문에 모두 재표시 해줘야함
    방법 2. 표시한곳을 따로 모아두고 다돌려보기
     -> 한번만 해주면됨
 */