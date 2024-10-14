import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.System.out
import java.math.BigInteger
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.log
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

private lateinit var reader: BufferedReader
private var tokenizer: StringTokenizer? = null

private fun read(): String {
    while (tokenizer == null || !tokenizer!!.hasMoreTokens()) {
        tokenizer = StringTokenizer(readLn())
    }
    return tokenizer!!.nextToken()
}

private fun readInt() = read().toInt()
private fun readLong() = read().toLong()
private fun readLn() = reader.readLine()!!
private fun readInts() = readLn().split(" ").map { it.toInt() }
private fun readInts(sz: Int) = Array(sz) { readInt() }
private fun readLongs() = readLn().split(" ").map { it.toLong() }
private fun readLongs(sz: Int) = Array(sz) { readLong() }
private operator fun String.times(n: Int): String {
    val sb = StringBuilder()
    repeat(n) {
        sb.append(this)
    }
    return sb.toString()
}

fun main() {
    reader = BufferedReader(InputStreamReader(System.`in`))
    solve()
    out.close()
}

fun solve() {
    val (n, m, q) = readInts()
    val eq = mutableListOf<Int>()
    val ieq = mutableListOf<Int>()
    eq.add(1)
    for (i in 2..n) {
        println("? 1 $i")
        val res = readInt()
        if (res == 0) {
            eq.add(i)
        } else if (res == 1) {
            ieq.add(i)
        }
    }

    if (eq.size == m) {
        println("! ${eq.joinToString(" ")}")
    } else if (ieq.size == m) {
        println("! ${ieq.joinToString(" ")}")
    }
    
}