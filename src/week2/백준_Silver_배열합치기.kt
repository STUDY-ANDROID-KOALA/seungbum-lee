package week2

private val bw = System.`out`.bufferedWriter()
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr1 = readLine().split(" ").map { it.toInt() }
    val arr2 = readLine().split(" ").map { it.toInt() }
    var index1 = 0
    var index2 = 0

    while (index1 < n && index2 < m) {
        if (arr1[index1] < arr2[index2]) {
            bw.write("${arr1[index1]} ")
            index1++
        } else {
            bw.write("${arr2[index2]} ")
            index2++
        }
    }


    while (index1 < n) {
        bw.write("${arr1[index1]} ")
        index1++
    }

    while (index2 < m) {
        bw.write("${arr2[index2]} ")
        index2++
    }

    bw.flush()
    bw.close()
}