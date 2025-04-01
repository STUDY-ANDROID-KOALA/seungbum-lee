package week1

fun main() = with(System.`in`.bufferedReader()) {
    val (a, b, c) = readLine().split(" ").map { it.toLong() }
    println(recursive(a, b, c))
}

fun recursive(a: Long, b: Long, c: Long): Long {
    if (b == 1L) return a % c
    val half = recursive(a, b / 2, c)
    val result = half * half % c
    return if (b % 2 == 0L) result else result * a % c
}