package week4

import kotlin.math.max

private val bw = System.`out`.bufferedWriter()
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val stairs = IntArray(n) { readLine().toInt() }
    val dp = Array(n + 1) { IntArray(3) }
    if(n==1) {
        print(stairs[0])
        return
    }
    dp[1][1] = stairs[0]
    dp[1][2] = 0
    dp[2][1] = stairs[1]
    dp[2][2] = dp[1][1] + stairs[1]
    for (i in 3..n) {
        dp[i][1] = max(dp[i - 2][1], dp[i - 2][2])+stairs[i-1]
        dp[i][2]= dp[i-1][1]+stairs[i-1]
    }

    bw.append("${max(dp[n][1],dp[n][2])}")
    bw.flush()
    bw.close()
}

/*
    1.테이블 만들기
    dp[i] = 계단 오으기 게임에서 얻을 수 잇는 총점수의 최대값 , count
    dp[1] = 꼭대기
    dp[2] = 꼭대기 +자기 계단
    dp[3] = dp[1]+1

    dp[6] = dp[5]+1 , dp[4]+1 -> count가 pair로 필요하겠네
    조건은 3계단은 연속으로 못밟음
     전계단을 밟을 수있다면
    2. 점화식 만들기
    dp[i] = max(if(count = 1dp[i-1] , ,dp[i-2]+1
 */