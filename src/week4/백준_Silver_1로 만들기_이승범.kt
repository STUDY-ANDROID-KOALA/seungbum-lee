package week4

import kotlin.math.min

private val bw = System.`out`.bufferedWriter()
private lateinit var dp :IntArray

fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    dp = IntArray(n+1)
    dp[1] =0
    for(i in 2 .. n){
        dp[i] = min(dp[i-1]+1,min(if(i%3==0)dp[i/3]+1 else 10000,if(i%2==0)dp[i/2]+1 else 10000))
    }
    bw.write("${dp[n]}")
    bw.flush()
    close()
}
/*
    1. 테이블 정의하기
    D[i] = i 를 1로 만들기 위해 필요한 연산 사용 횟수의 최솟값
    그리고 특정횟수를 구한다고 생각하기 예를 들어 나는 D[i-1]번째 까지의 최솟값을 알고잇는거임
    이때 내가 할수있는거는 12에서 할 수 있는 연산을 생각해보기
    3으로 나누거나 D[4] + 1
    2로 나누거나 D[6] +1
    1을 빼거나 D[11]+1
    D[12] = min(D[4],D[6]+1,D[11]+1
    2. 점화식 찾기
 */