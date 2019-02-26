package com.github.isturdy.aiflagtool

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class RingBufferTest {
    @Test
    fun getSize() {
        val ring = RingBuffer<Int>(10)
        assertEquals(0, ring.size)

        ring.add(1)
        assertEquals(1, ring.size)

        (0..100).forEach(ring::add)
        assertEquals(10, ring.size)
    }

    @Test
    fun peek() {
        val ring = RingBuffer<Int>(10)
        for (i in 0..20) {
            ring.add(i)
            assertEquals(i, ring.peek())
        }
    }

    @Test
    fun get() {
        val ring = RingBuffer<Int>(10)
        (0..20).forEach(ring::add)

        for (i in 0 until 10) {
            assertEquals(20 - i, ring.get(i))
        }
    }

    @Test
    fun iteratorPartial() {
        val ring = RingBuffer<Int>(5)
        ring.add(0)
        ring.add(1)
        ring.add(2)

        assertEquals(listOf(0, 1, 2), ring.toList())
    }

    @Test
    fun iterator() {
        val ring = RingBuffer<Int>(5)
        (0..8).forEach(ring::add)

        assertEquals(listOf(4, 5, 6, 7, 8), ring.toList())
    }

}