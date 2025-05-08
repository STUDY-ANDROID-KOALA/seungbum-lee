package week5

private val bw = System.`out`.bufferedWriter()

fun main() = with(System.`in`.bufferedReader()){
    val (n,price) = readLine().split(" ").map{it.toInt()}
    val coin = IntArray(n)
    var maxNum = 0
    var goal = price

    repeat(n){idx->
        coin[idx] = readLine().toInt()
    }

    for(i in n-1 downTo 0){
        while(goal>=coin[i]){
            goal -= coin[i]
            maxNum++
        }
    }

    bw.append("$maxNum")
    bw.flush()
}