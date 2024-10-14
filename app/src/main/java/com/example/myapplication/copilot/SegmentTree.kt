package com.example.myapplication.copilot

class SegmentTree(private val n: Int) {
    private val tree: IntArray = IntArray(4 * n)

    // Initialize the segment tree with initial values
    fun build(arr: IntArray, v: Int = 1, tl: Int = 0, tr: Int = n - 1) {
        if (tl == tr) {
            tree[v] = arr[tl]
        } else {
            val tm = (tl + tr) / 2
            build(arr, 2 * v, tl, tm)
            build(arr, 2 * v + 1, tm + 1, tr)
            tree[v] = combine(tree[2 * v], tree[2 * v + 1]) // Define your own combining function
        }
    }

    // Query the segment tree for a given range [l, r]
    fun query(l: Int, r: Int, v: Int = 1, tl: Int = 0, tr: Int = n - 1): Int {
        if (l > r) return neutralValue // Define your own neutral value
        if (l == tl && r == tr) return tree[v]
        val tm = (tl + tr) / 2
        return combine(query(l, minOf(r, tm), 2 * v, tl, tm),
            query(maxOf(l, tm + 1), r, 2 * v + 1, tm + 1, tr))
    }

    // Update the segment tree for a given index i with value x
    fun update(i: Int, x: Int, v: Int = 1, tl: Int = 0, tr: Int = n - 1) {
        if (tl == tr) {
            tree[v] = x
        } else {
            val tm = (tl + tr) / 2
            if (i <= tm) {
                update(i, x, 2 * v, tl, tm)
            } else {
                update(i, x, 2 * v + 1, tm + 1, tr)
            }
            tree[v] = combine(tree[2 * v], tree[2 * v + 1]) // Define your own combining function
        }
    }

    // Define your own combining function (e.g., sum, min, max, etc.)
    private fun combine(a: Int, b: Int): Int {
        return a + b // Example: Sum of two values
    }

    // Define your own neutral value (e.g., 0 for sum, Int.MAX_VALUE for min, etc.)
    private val neutralValue: Int = 0
}

fun main() {
    val n = 10 // Size of the array
    val arr = intArrayOf(1, 3, 5, 7, 9, 11, 13, 15, 17, 19)

    val segmentTree = SegmentTree(n)
    segmentTree.build(arr)

    // Example queries
    println(segmentTree.query(2, 6)) // Query sum in range [2, 6]
    segmentTree.update(4, 8) // Update value at index 4 to 8
    println(segmentTree.query(0, 9)) // Query sum in the entire array
}
