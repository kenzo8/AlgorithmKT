package com.example.myapplication.chatgpt

class SegmentTreeLazy(val arr: LongArray) {
    val tree: LongArray
    val lazy: LongArray

    init {
        val n = arr.size
        tree = LongArray(4 * n)
        lazy = LongArray(4 * n)
        build(1, 0, n - 1)
    }

    fun build(node: Int, start: Int, end: Int) {
        if (start == end) {
            tree[node] = arr[start]
        } else {
            val mid = (start + end) / 2
            build(node * 2, start, mid)
            build(node * 2 + 1, mid + 1, end)
            tree[node] = tree[node * 2] + tree[node * 2 + 1]
        }
    }

    fun queryLazy(node: Int, start: Int, end: Int, l: Int, r: Int): Long {
        if (lazy[node] != 0L) {
            tree[node] += (end - start + 1) * lazy[node]
            if (start != end) {
                lazy[node * 2] += lazy[node]
                lazy[node * 2 + 1] += lazy[node]
            }
            lazy[node] = 0L
        }

        if (start > r || end < l)
            return 0L
        if (start >= l && end <= r)
            return tree[node]

        val mid = (start + end) / 2
        val p1 = queryLazy(node * 2, start, mid, l, r)
        val p2 = queryLazy(node * 2 + 1, mid + 1, end, l, r)
        return p1 + p2
    }

    fun updateRangeLazy(node: Int, start: Int, end: Int, l: Int, r: Int, diff: Long) {
        if (lazy[node] != 0L) {
            tree[node] += (end - start + 1) * lazy[node]
            if (start != end) {
                lazy[node * 2] += lazy[node]
                lazy[node * 2 + 1] += lazy[node]
            }
            lazy[node] = 0L
        }

        if (start > end || start > r || end < l)
            return

        if (start >= l && end <= r) {
            tree[node] += (end - start + 1) * diff
            if (start != end) {
                lazy[node * 2] += diff
                lazy[node * 2 + 1] += diff
            }
            return
        }

        val mid = (start + end) / 2
        updateRangeLazy(node * 2, start, mid, l, r, diff)
        updateRangeLazy(node * 2 + 1, mid + 1, end, l, r, diff)
        tree[node] = tree[node * 2] + tree[node * 2 + 1]
    }

    fun updateRange(l: Int, r: Int, diff: Long) {
        updateRangeLazy(1, 0, arr.size - 1, l, r, diff)
    }

    fun query(l: Int, r: Int): Long {
        return queryLazy(1, 0, arr.size - 1, l, r)
    }
}

fun main() {
    val arr = longArrayOf(1L, 3L, 5L, 7L, 9L, 11L)
    val segTree = SegmentTreeLazy(arr)
    println("Sum of values in given range = ${segTree.query(1, 3)}")
    segTree.updateRange(1, 5, 10)
    println("Updated sum of values in given range = ${segTree.query(1, 3)}")
}
