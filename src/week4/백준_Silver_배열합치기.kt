package week4

private val bw = System.`out`.bufferedWriter()


fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val A = readLine().split(" ").map { it.toInt() }
    val B = readLine().split(" ").map { it.toInt() }

    var idxA = 0
    var idxB = 0
    for (i in 0..<n + m) {
        if (idxA == n || idxB == m) break
        if (A[idxA] < B[idxB]) {
            bw.append("${A[idxA++]} ")
        } else {
            bw.append("${B[idxB++]} ")
        }
    }

    if (idxA == n) for (i in idxB..<m) bw.append("${B[idxB++]} ")
    if (idxB == m) for (i in idxA..<n) bw.append("${A[idxA++]} ")

    bw.flush()
    close()
}