package week4

private val bw = System.`out`.bufferedWriter()
private lateinit var dp :IntArray

fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    dp = IntArray(12)
    dp[1] =1
    dp[2] = 2
    dp[3] = 4
    for(i in 4..11){
        dp[i] =dp[i-1]+dp[i-2]+dp[i-3]
    }
    repeat(n){
        bw.append("${dp[readLine().toInt()]}\n")
    }

    bw.flush()
    close()
}

/*
    1. 테이블 구하기
    D[i] = i 를 구하는 1,2,3의 합으로 나타내는 방법의 수
    D[6] = D[5] + D[4] + D[3]

    2. 점화식 구하기
    dp[i] = min(dp[i-1]+1,min(dp[i-2]+1.dp[i-3]+3))
 */