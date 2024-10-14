package com.example.myapplication.chatgpt

import java.util.*

class Graph {
    private val adjacencyList: MutableMap<Int, MutableList<Pair<Int, Long>>> = mutableMapOf()

    fun addEdge(u: Int, v: Int, w: Long) {
        adjacencyList.computeIfAbsent(u) { mutableListOf() }.add(v to w)
//        adjacencyList.computeIfAbsent(v) { mutableListOf() }.add(u to w)
    }

    fun dijkstra(start: Int): Map<Int, Long> {
        val distances: MutableMap<Int, Long> = mutableMapOf()
        val priorityQueue = PriorityQueue<Pair<Int, Long>>(compareBy { it.second })

        priorityQueue.add(start to 0)
        distances[start] = 0

        while (priorityQueue.isNotEmpty()) {
            val (current, currentDistance) = priorityQueue.poll()!!

            if (currentDistance > (distances[current] ?: Long.MAX_VALUE)) continue

            for ((neighbour, weight) in adjacencyList[current] ?: emptyList()) {
                val distance = currentDistance + weight
                if (distance < (distances[neighbour] ?: Long.MAX_VALUE)) {
                    distances[neighbour] = distance
                    priorityQueue.add(neighbour to distance)
                }
            }
        }

        return distances
    }
}

fun solve() {
    val n = 5
    val graph = Graph()
    for (i in 1 until n) {
//        val (a, b, x) = readInts()
//        graph.addEdge(i, i + 1, a.toLong())
//
//        graph.addEdge(i, x, b.toLong())
    }
    val startNode = 1
    val distances = graph.dijkstra(startNode)
//    println("Shortest distances from node $startNode:")
//    for ((node, distance) in distances) {
//        println("Node $node: Distance = $distance")
//    }
    println(distances[n])
}
