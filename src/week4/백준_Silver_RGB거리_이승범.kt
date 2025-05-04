package week4

import java.util.*
import kotlin.math.min

/*
    N개의 줄에는 집을 RGB로 칠하는 비용이 집마다 주어진다.
    D[i] 는 i번째 집까지 칠하는 비용의 최솟값
    이전집의 색과 같으면 안된다. -> 반드시 DP겠네
    같으면 어떡해? -> bfs
    1. 테이블 만들기
    D[i][0] = i번째 집까지 칠할때 비용의 최솟값 , i번째 집이 빨강
    D[i][1] = i번째 집까지 칠할때 비용의 최솟값 , i번째 집이 초록
    D[i][2] = i번째 집까지 칠할때 비용의 최솟값 , i번째 집이 파랑
    2. 점화식 만들기
    D[i][0] =min( D[i][1].D[i][2]) +빨강집

 */

private val bw = System.`out`.bufferedWriter()

fun main() = with(System.`in`.bufferedReader()) {

    val n = readLine().toInt()
    val dp = Array(n + 1) { IntArray(3) } // 1 : DP 값 2: 이전에 선택한 값 3: 중복여부
    val st = StringTokenizer(readLine())
    repeat(3) { idx ->
        dp[1][idx] = st.nextToken().toInt()
    }
    for (i in 2..n) {
        val rgb = StringTokenizer(readLine())
        dp[i][0] = min(dp[i - 1][1], dp[i - 1][2]) + rgb.nextToken().toInt()
        dp[i][1] = min(dp[i - 1][0], dp[i - 1][2]) + rgb.nextToken().toInt()
        dp[i][2] = min(dp[i - 1][1], dp[i - 1][0]) + rgb.nextToken().toInt()
    }
    bw.append("${min(dp[n][0], min(dp[n][1], dp[n][2]))}")
    bw.flush()
    close()
}
