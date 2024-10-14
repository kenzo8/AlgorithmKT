package com.example.myapplication.template

import java.io.BufferedReader
import java.util.*

lateinit var reader: BufferedReader
var tokenizer: StringTokenizer? = null

fun read(): String {
    while (tokenizer == null || !tokenizer!!.hasMoreTokens()) {
        tokenizer = StringTokenizer(readLn())
    }
    return tokenizer!!.nextToken()
}

fun readInt() = read().toInt()
fun readLong() = read().toLong()
fun readLn() = reader.readLine()!!
fun readInts() = readLn().split(" ").map { it.toInt() }
fun readInts(sz: Int) = Array(sz) { readInt() }
fun readLongs() = readLn().split(" ").map { it.toLong() }
fun readLongs(sz: Int) = Array(sz) { readLong() }

private operator fun String.times(n: Int): String {
    val sb = StringBuilder()
    repeat(n) {
        sb.append(this)
    }
    return sb.toString()
}
