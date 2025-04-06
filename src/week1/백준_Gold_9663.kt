package week1

private val bw = System.`out`.bufferedWriter()
private var n = 0
private var count = 0
private lateinit var column: BooleanArray
private lateinit var rightDiag: BooleanArray
private lateinit var leftDiag: BooleanArray

fun main() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()

    column = BooleanArray(n + 1)
    rightDiag = BooleanArray(2 * n + 1)
    leftDiag = BooleanArray(2 * n + 1)

    backTracking(1)
    bw.write("$count")
    bw.flush()
    bw.close()
}

fun backTracking(row: Int) {
    if (row == n + 1) {
        count++
        return
    }
    for (i in 1..n) {
        if (column[i] || rightDiag[row + i] || leftDiag[row - i + n]) continue

        column[i] = true
        rightDiag[row + i] = true
        leftDiag[row - i + n] = true

        backTracking(row + 1)

        column[i] = false
        rightDiag[row + i] = false
        leftDiag[row - i + n] = false

    }
}


/*
    오른쪽 대각선은 x-y가 같으면 갈은곳이다
    왼쪽대각선은 x+y가 같으면 같은곳에 위치한다.
    방법 1. 모두 다 표시
     -> 백트레킹이기때문에 모두 재표시 해줘야함
    방법 2. 표시한곳을 따로 모아두고 다돌려보기
     -> 한번만 해주면됨
 */